package com.intouch.Inventory.services;

import com.intouch.Inventory.dto.AssetDto;
import com.intouch.Inventory.entity.Asset;
import com.intouch.Inventory.entity.AssetType;
import com.intouch.Inventory.exception.ResourceNotFoundException;
import com.intouch.Inventory.repository.AssetRepository;
import com.intouch.Inventory.repository.AssetTransactionRepository;
import com.intouch.Inventory.repository.AssetTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock AssetRepository repo;
    @Mock AssetTypeRepository typeRepo;
    @Mock AssetTransactionRepository txRepo;
    @InjectMocks AssetService svc;

    @Test
    void create_InvalidType_Throws() {
        AssetDto dto = new AssetDto();
        dto.setTypeId(99L);
        when(typeRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> svc.create(dto));
    }

    @Test
    void create_Valid_ReturnsDto() {
        AssetDto dto = new AssetDto(null, "TAG001", "HOST1", 1L, "desc", "AVAILABLE", "LOC");
        AssetType type = new AssetType(1L, "COMPUTER");

        when(typeRepo.findById(1L)).thenReturn(Optional.of(type));
        when(repo.save(any(Asset.class))).thenAnswer(inv -> {
            Asset a = inv.getArgument(0);
            a.setId(10L);
            return a;
        });

        AssetDto result = svc.create(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("TAG001", result.getAssetTag());
        verify(repo).save(any(Asset.class));
    }

    @Test
    void getById_NotFound_Throws() {
        when(repo.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> svc.getById(5L));
    }

    @Test
    void getAll_ReturnsList() {
        Asset a1 = new Asset(); a1.setId(1L); a1.setAssetTag("T1"); a1.setStatus("AVAILABLE");
        Asset a2 = new Asset(); a2.setId(2L); a2.setAssetTag("T2"); a2.setStatus("LOANED");
        when(repo.findAll()).thenReturn(List.of(a1, a2));

        List<AssetDto> list = svc.getAll();
        assertEquals(2, list.size());
        assertEquals("T1", list.get(0).getAssetTag());
    }
}
