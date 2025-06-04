package com.intouch.Inventory.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AssetDto {
    private Long id;

    @NotBlank(message = "El assetTag es obligatorio")
    private String assetTag;

    private String hostname;

    private Long typeId;

    private String description;

    private String status;

    private String location;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // =======================
    //   Constructor vacío
    // =======================
    public AssetDto() {
        // para que Hibernate / Jackson / pruebas (sin argumentos) funcionen
    }

    // ================================================================
    //  Constructor “de conveniencia” para que el AssetServiceTest compile
    //
    //  El test invoca algo como:
    //     new AssetDto(null, "PC-001", "DESKTOP-001", 1L, "desc", "AVAILABLE", "OficinaA");
    //  Por eso aquí se acepta 'long typeId' (primitivo) y luego lo castea a Long.
    // ================================================================
    public AssetDto(Long id,
                    String assetTag,
                    String hostname,
                    long typeId,
                    String description,
                    String status,
                    String location) {
        this.id = id;
        this.assetTag = assetTag;
        this.hostname = hostname;
        this.typeId = typeId;       // se autoboxea a Long
        this.description = description;
        this.status = status;
        this.location = location;
    }

    // =======================
    //    Getters / Setters
    // =======================

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
