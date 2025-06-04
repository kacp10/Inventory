package com.intouch.Inventory.controller;

import com.intouch.Inventory.dto.AssetTypeDto;
import com.intouch.Inventory.services.AssetTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para manejar todos los endpoints de AssetType.
 */
@RestController
@RequestMapping("/api/asset-types")
public class AssetTypeController {

    private final AssetTypeService service;

    public AssetTypeController(AssetTypeService service) {
        this.service = service;
    }

    /**
     * GET /api/asset-types
     * Devuelve todos los AssetType disponibles.
     */
    @GetMapping
    public ResponseEntity<List<AssetTypeDto>> getAll() {
        List<AssetTypeDto> all = service.getAll();
        return ResponseEntity.ok(all);
    }

    /**
     * GET /api/asset-types/{id}
     * Devuelve un AssetType por su ID.
     * Si no existe, lanza ResourceNotFoundException (capturado por tu GlobalExceptionHandler).
     */
    @GetMapping("/{id}")
    public ResponseEntity<AssetTypeDto> getById(@PathVariable Long id) {
        AssetTypeDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * POST /api/asset-types
     * Crea un nuevo AssetType.
     * Body: { "name": "TABLET" }
     * Si el JSON enviado no cumple validación (@NotBlank), Spring lanzará 400 automáticamente.
     */
    @PostMapping
    public ResponseEntity<AssetTypeDto> create(@Valid @RequestBody AssetTypeDto dto) {
        AssetTypeDto created = service.create(dto);
        // 201 Created con Location apuntando a /api/asset-types/{idNuevo}
        return ResponseEntity
                .created(URI.create("/api/asset-types/" + created.getId()))
                .body(created);
    }

    /**
     * PUT /api/asset-types/{id}
     * Actualiza el AssetType con ID dado.
     * Body: { "name": "TABLET-PRO" }
     * Si no existe el ID, el service arroja ResourceNotFoundException → 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<AssetTypeDto> update(
            @PathVariable Long id,
            @Valid @RequestBody AssetTypeDto dto) {

        AssetTypeDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/asset-types/{id}
     * Elimina el AssetType con ID dado.
     * Si no existe el ID, el service arroja ResourceNotFoundException → 404 Not Found
     * Si borra con éxito, devuelve 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
