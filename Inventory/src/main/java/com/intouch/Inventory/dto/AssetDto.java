package com.intouch.Inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AssetDto {
    private Long id;

    @NotBlank(message = "El assetTag es obligatorio")
    private String assetTag;

    private String hostname;

    @NotNull(message = "El typeId no puede ser nulo")
    private Long typeId;

    private String description;

    @NotBlank(message = "El status es obligatorio")
    private String status;

    private String location;

    public AssetDto() {
    }

    public AssetDto(Long id,
                    String assetTag,
                    String hostname,
                    Long typeId,
                    String description,
                    String status,
                    String location) {
        this.id = id;
        this.assetTag = assetTag;
        this.hostname = hostname;
        this.typeId = typeId;
        this.description = description;
        this.status = status;
        this.location = location;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Long getTypeId() {
        return typeId;
    }
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
