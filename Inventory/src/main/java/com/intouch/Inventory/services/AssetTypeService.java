package com.intouch.Inventory.services;

import com.intouch.Inventory.dto.AssetTypeDto;
import com.intouch.Inventory.entity.AssetType;
import com.intouch.Inventory.exception.ResourceNotFoundException;
import com.intouch.Inventory.repository.AssetTypeRepository;
import com.intouch.Inventory.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar AssetType.
 */
@Service
@Transactional
public class AssetTypeService {

    private final AssetTypeRepository repo;

    public AssetTypeService(AssetTypeRepository repo) {
        this.repo = repo;
    }

    /** Listar todos los tipos */
    public List<AssetTypeDto> getAll() {
        return repo.findAll()
                .stream()
                .map(MapperUtil::toAssetTypeDto)
                .collect(Collectors.toList());
    }

    /** Obtener uno por id */
    public AssetTypeDto getById(Long id) {
        AssetType entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetType", "id", id));
        return MapperUtil.toAssetTypeDto(entity);
    }

    /** Crear uno nuevo */
    public AssetTypeDto create(AssetTypeDto dto) {
        // Convierte DTO → entidad
        AssetType entity = MapperUtil.toAssetTypeEntity(dto);
        AssetType saved = repo.save(entity);
        return MapperUtil.toAssetTypeDto(saved);
    }

    /** Actualizar existente */
    public AssetTypeDto update(Long id, AssetTypeDto dto) {
        AssetType existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetType", "id", id));
        existing.setName(dto.getName());
        AssetType saved = repo.save(existing);
        return MapperUtil.toAssetTypeDto(saved);
    }

    /** Borrar por id */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("AssetType", "id", id);
        }
        repo.deleteById(id);
    }
}
