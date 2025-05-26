package com.intouch.Inventory.util;

import com.intouch.Inventory.dto.*;
import com.intouch.Inventory.entity.*;

public class MapperUtil {
    public static AssetDto toAssetDto(Asset a) {
        AssetDto dto = new AssetDto();
        dto.setId(a.getId());
        dto.setAssetTag(a.getAssetTag());
        dto.setHostname(a.getHostname());
        dto.setTypeId(a.getType().getId());
        dto.setDescription(a.getDescription());
        dto.setStatus(a.getStatus());
        dto.setLocation(a.getLocation());
        return dto;
    }

    public static Asset toAsset(AssetDto dto) {
        Asset a = new Asset();
        a.setAssetTag(dto.getAssetTag());
        a.setHostname(dto.getHostname());
        a.setDescription(dto.getDescription());
        a.setStatus(dto.getStatus());
        a.setLocation(dto.getLocation());
        return a;
    }

    public static AssetTransactionDto toTransactionDto(AssetTransaction t) {
        AssetTransactionDto dto = new AssetTransactionDto();
        dto.setId(t.getId());
        dto.setAssetId(t.getAsset().getId());
        dto.setAction(t.getAction());
        dto.setPerformedBy(t.getPerformedBy());
        dto.setTimestamp(t.getTimestamp());
        dto.setNotes(t.getNotes());
        return dto;
    }
}
