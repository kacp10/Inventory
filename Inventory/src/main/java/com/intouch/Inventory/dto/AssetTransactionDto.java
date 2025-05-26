package com.intouch.Inventory.dto;

import java.time.LocalDateTime;

public class AssetTransactionDto {
    private Long id;
    private Long assetId;
    private String action;
    private String performedBy;
    private LocalDateTime timestamp;
    private String notes;

    public AssetTransactionDto() {
    }

    public AssetTransactionDto(Long id,
                               Long assetId,
                               String action,
                               String performedBy,
                               LocalDateTime timestamp,
                               String notes) {
        this.id = id;
        this.assetId = assetId;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetId() {
        return assetId;
    }
    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getPerformedBy() {
        return performedBy;
    }
    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
