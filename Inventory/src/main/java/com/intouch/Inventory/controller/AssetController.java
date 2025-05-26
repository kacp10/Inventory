package com.intouch.Inventory.controller;

import com.intouch.Inventory.dto.AssetDto;
import com.intouch.Inventory.services.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService svc;
    public AssetController(AssetService svc){ this.svc = svc; }

    @GetMapping
    public List<AssetDto> list(@RequestParam(required=false) String status) {
        return status==null ? svc.getAll() : svc.findByStatus(status);
    }

    @GetMapping("/{id}")
    public AssetDto get(@PathVariable Long id) {
        return svc.getById(id);
    }

    @PostMapping
    public ResponseEntity<AssetDto> create(@Valid @RequestBody AssetDto dto) {
        AssetDto created = svc.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public AssetDto update(@PathVariable Long id, @Valid @RequestBody AssetDto dto) {
        return svc.update(id,dto);
    }

    @PostMapping("/{id}/loan")
    public AssetDto loan(@PathVariable Long id,
                         @RequestParam String user,
                         @RequestParam(required=false) String notes) {
        return svc.loan(id,user,notes);
    }

    @PostMapping("/{id}/return")
    public AssetDto returnAsset(@PathVariable Long id,
                                @RequestParam String user,
                                @RequestParam(required=false) String notes) {
        return svc.returnAsset(id,user,notes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}
