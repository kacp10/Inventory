package com.intouch.Inventory.services;

import com.intouch.Inventory.dto.AssetTypeDto;
import com.intouch.Inventory.entity.AssetType;
import com.intouch.Inventory.exception.ResourceNotFoundException;
import com.intouch.Inventory.repository.AssetTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssetTypeService {

    private final AssetTypeRepository repo;

    public AssetTypeService(AssetTypeRepository repo) {
        this.repo = repo;
    }

    /** Listar todos los tipos */
    public List<AssetTypeDto> getAll() {
        return repo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /** Obtener uno por id */
    public AssetTypeDto getById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("AssetType", "id", id));
    }

    /** Crear uno nuevo */
    public AssetTypeDto create(AssetTypeDto dto) {
        AssetType entity = new AssetType();
        entity.setName(dto.getName());
        AssetType saved = repo.save(entity);
        return toDto(saved);
    }

    /** Actualizar existente */
    public AssetTypeDto update(Long id, AssetTypeDto dto) {
        AssetType existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetType", "id", id));
        existing.setName(dto.getName());
        AssetType saved = repo.save(existing);
        return toDto(saved);
    }

    /** Borrar por id */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("AssetType", "id", id);
        }
        repo.deleteById(id);
    }

    /** Conversión Entity → DTO */
    private AssetTypeDto toDto(AssetType e) {
        AssetTypeDto d = new AssetTypeDto();
        d.setId(e.getId());
        d.setName(e.getName());
        return d;
    }
}
