package com.intouch.Inventory.services;

import com.intouch.Inventory.dto.AssetTransactionDto;
import com.intouch.Inventory.entity.AssetTransaction;
import com.intouch.Inventory.exception.ResourceNotFoundException;
import com.intouch.Inventory.repository.AssetRepository;
import com.intouch.Inventory.repository.AssetTransactionRepository;
import com.intouch.Inventory.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetTransactionService {

    private final AssetTransactionRepository txRepo;
    private final AssetRepository assetRepo;

    public AssetTransactionService(AssetTransactionRepository txRepo,
                                   AssetRepository assetRepo) {
        this.txRepo = txRepo;
        this.assetRepo = assetRepo;
    }

    /**
     * Listar todas las transacciones de un activo dado.
     */
    public List<AssetTransactionDto> getByAssetId(Long assetId) {
        if (!assetRepo.existsById(assetId)) {
            throw new ResourceNotFoundException("Asset", "id", assetId);
        }
        return txRepo.findByAssetIdOrderByTimestampDesc(assetId)
                .stream()
                .map(MapperUtil::toTransactionDto)
                .collect(Collectors.toList());
    }

    /**
     * Registrar manualmente una transacción (si la necesitas).
     */
    public AssetTransactionDto create(AssetTransactionDto dto) {
        // Validar que exista el asset
        var asset = assetRepo.findById(dto.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset", "id", dto.getAssetId()));

        AssetTransaction tx = new AssetTransaction();
        tx.setAsset(asset);
        tx.setAction(dto.getAction());
        tx.setPerformedBy(dto.getPerformedBy());
        tx.setNotes(dto.getNotes());

        AssetTransaction saved = txRepo.save(tx);
        return MapperUtil.toTransactionDto(saved);
    }
}
