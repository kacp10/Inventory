package com.intouch.Inventory.controller;

import com.intouch.Inventory.dto.AssetTransactionDto;
import com.intouch.Inventory.service.AssetTransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-transactions")
public class AssetTransactionController {

    private final AssetTransactionService svc;

    public AssetTransactionController(AssetTransactionService svc) {
        this.svc = svc;
    }

    /**
     * Listar historial de movimientos de un activo.
     * GET /api/asset-transactions?assetId={id}
     */
    @GetMapping
    public List<AssetTransactionDto> listByAsset(@RequestParam Long assetId) {
        return svc.getByAssetId(assetId);
    }

    /**
     * Registrar una nueva transacción manualmente.
     * POST /api/asset-transactions
     */
    @PostMapping
    public ResponseEntity<AssetTransactionDto> create(
            @Valid @RequestBody AssetTransactionDto dto
    ) {
        AssetTransactionDto created = svc.create(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }
}
