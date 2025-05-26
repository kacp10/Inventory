package com.intouch.Inventory.dto;

import jakarta.validation.constraints.NotBlank;

public class AssetTypeDto {
    private Long id;

    @NotBlank(message = "El nombre del tipo es obligatorio")
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
