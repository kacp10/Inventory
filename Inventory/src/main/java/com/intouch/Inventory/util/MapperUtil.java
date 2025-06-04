package com.intouch.Inventory.util;

import com.intouch.Inventory.dto.AssetDto;
import com.intouch.Inventory.dto.AssetTransactionDto;
import com.intouch.Inventory.dto.AssetTypeDto;
import com.intouch.Inventory.entity.Asset;
import com.intouch.Inventory.entity.AssetTransaction;
import com.intouch.Inventory.entity.AssetType;

public class MapperUtil {

    // ==================== AssetType ====================

    /** Convierte entidad AssetType → DTO */
    public static AssetTypeDto toAssetTypeDto(AssetType a) {
        AssetTypeDto dto = new AssetTypeDto();
        dto.setId(a.getId());
        dto.setName(a.getName());
        return dto;
    }

    /** Convierte DTO → entidad AssetType */
    public static AssetType toAssetTypeEntity(AssetTypeDto dto) {
        AssetType entity = new AssetType();
        entity.setName(dto.getName());
        return entity;
    }

    // ==================== Asset ====================

    /** Convierte entidad Asset → DTO */
    public static AssetDto toAssetDto(Asset a) {
        AssetDto dto = new AssetDto();
        dto.setId(a.getId());
        dto.setAssetTag(a.getAssetTag());
        dto.setHostname(a.getHostname());
        dto.setTypeId(a.getType().getId());
        dto.setDescription(a.getDescription());
        dto.setStatus(a.getStatus());
        dto.setLocation(a.getLocation());
        dto.setCreatedAt(a.getCreatedAt());   // ahora existe setter en AssetDto
        dto.setUpdatedAt(a.getUpdatedAt());   // ahora existe setter en AssetDto
        return dto;
    }

    /** Convierte DTO → entidad Asset (sin relaciones) */
    public static Asset toAssetEntity(AssetDto dto) {
        Asset a = new Asset();
        a.setAssetTag(dto.getAssetTag());
        a.setHostname(dto.getHostname());
        a.setDescription(dto.getDescription());
        a.setStatus(dto.getStatus());
        a.setLocation(dto.getLocation());
        // La relación con AssetType se setea en el Service
        return a;
    }

    // ==================== AssetTransaction ====================

    /** Convierte AssetTransaction entidad → DTO */
    public static AssetTransactionDto toTransactionDto(AssetTransaction t) {
        AssetTransactionDto dto = new AssetTransactionDto();
        dto.setId(t.getId());
        dto.setAssetId(t.getAsset().getId());
        dto.setAction(t.getAction());
        dto.setPerformedBy(t.getPerformedBy());
        dto.setTimestamp(t.getTimestamp());   // ahora existe setter en AssetTransactionDto
        dto.setNotes(t.getNotes());
        return dto;
    }

    /** Convierte DTO → entidad AssetTransaction (sin relaciones) */
    public static AssetTransaction toTransactionEntity(AssetTransactionDto dto) {
        AssetTransaction t = new AssetTransaction();
        t.setAction(dto.getAction());
        t.setPerformedBy(dto.getPerformedBy());
        t.setNotes(dto.getNotes());
        // El Service será el encargado de setear la asociación con Asset
        return t;
    }
}
