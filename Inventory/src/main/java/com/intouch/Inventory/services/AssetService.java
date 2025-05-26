package com.intouch.Inventory.services;

import com.intouch.Inventory.dto.AssetDto;
import com.intouch.Inventory.entity.Asset;
import com.intouch.Inventory.entity.AssetTransaction;
import com.intouch.Inventory.entity.AssetType;
import com.intouch.Inventory.exception.ResourceNotFoundException;
import com.intouch.Inventory.repository.AssetRepository;
import com.intouch.Inventory.repository.AssetTransactionRepository;
import com.intouch.Inventory.repository.AssetTypeRepository;
import com.intouch.Inventory.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetService {
    private final AssetRepository repo;
    private final AssetTypeRepository typeRepo;
    private final AssetTransactionRepository txRepo;

    public AssetService(AssetRepository repo,
                        AssetTypeRepository typeRepo,
                        AssetTransactionRepository txRepo) {
        this.repo = repo;
        this.typeRepo = typeRepo;
        this.txRepo = txRepo;
    }

    public List<AssetDto> getAll() {
        return repo.findAll().stream()
                .map(MapperUtil::toAssetDto)
                .collect(Collectors.toList());
    }

    public AssetDto getById(Long id) {
        return repo.findById(id)
                .map(MapperUtil::toAssetDto)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
    }

    public List<AssetDto> findByStatus(String status) {
        return repo.findByStatus(status).stream()
                .map(MapperUtil::toAssetDto)
                .collect(Collectors.toList());
    }

    public AssetDto create(AssetDto dto) {
        AssetType type = typeRepo.findById(dto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetType", "id", dto.getTypeId()));
        Asset a = MapperUtil.toAsset(dto);
        a.setType(type);

        // Guarda la entidad
        Asset saved = repo.save(a);

        // Retorna el DTO correctamente (no como referencia)
        return MapperUtil.toAssetDto(saved);
    }

    public AssetDto update(Long id, AssetDto dto) {
        Asset existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
        existing.setAssetTag(dto.getAssetTag());
        existing.setHostname(dto.getHostname());
        existing.setDescription(dto.getDescription());
        existing.setStatus(dto.getStatus());
        existing.setLocation(dto.getLocation());

        if (!existing.getType().getId().equals(dto.getTypeId())) {
            AssetType newType = typeRepo.findById(dto.getTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("AssetType", "id", dto.getTypeId()));
            existing.setType(newType);
        }

        Asset updated = repo.save(existing);
        return MapperUtil.toAssetDto(updated);
    }

    private AssetDto changeStatus(Long id, String action, String user, String notes) {
        Asset a = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", id));
        a.setStatus("RETURN".equals(action) ? "AVAILABLE" : action);
        repo.save(a);

        AssetTransaction tx = new AssetTransaction();
        tx.setAsset(a);
        tx.setAction(action);
        tx.setPerformedBy(user);
        tx.setNotes(notes);
        txRepo.save(tx);

        return MapperUtil.toAssetDto(a);
    }

    public AssetDto loan(Long id, String user, String notes) {
        return changeStatus(id, "LOANED", user, notes);
    }

    public AssetDto returnAsset(Long id, String user, String notes) {
        return changeStatus(id, "RETURN", user, notes);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Asset", "id", id);
        }
        repo.deleteById(id);
    }
}
