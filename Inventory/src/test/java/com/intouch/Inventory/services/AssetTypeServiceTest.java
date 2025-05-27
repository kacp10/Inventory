package com.intouch.Inventory.services;

import com.intouch.Inventory.dto.AssetTypeDto;
import com.intouch.Inventory.entity.AssetType;
import com.intouch.Inventory.exception.ResourceNotFoundException;
import com.intouch.Inventory.repository.AssetTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssetTypeServiceTest {

    @Mock
    private AssetTypeRepository repo;

    @InjectMocks
    private AssetTypeService svc;

    @Test
    void getAll_ReturnsDtos() {
        AssetType t1 = new AssetType();
        t1.setId(1L);
        t1.setName("COMPUTER");
        AssetType t2 = new AssetType();
        t2.setId(2L);
        t2.setName("MONITOR");
        when(repo.findAll()).thenReturn(List.of(t1, t2));

        List<AssetTypeDto> list = svc.getAll();

        assertEquals(2, list.size());
        assertEquals(1L, list.get(0).getId());
        assertEquals("COMPUTER", list.get(0).getName());
        assertEquals(2L, list.get(1).getId());
        assertEquals("MONITOR", list.get(1).getName());
    }

    @Test
    void getById_NotFound_Throws() {
        when(repo.findById(9L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> svc.getById(9L));
    }

    @Test
    void create_Success() {
        // Construimos el DTO con el setter
        AssetTypeDto dto = new AssetTypeDto();
        dto.setName("PRINTER");

        // Simulamos la entidad guardada con ID
        AssetType savedEntity = new AssetType();
        savedEntity.setId(7L);
        savedEntity.setName("PRINTER");
        when(repo.save(any(AssetType.class))).thenReturn(savedEntity);

        AssetTypeDto result = svc.create(dto);

        assertEquals(7L, result.getId());
        assertEquals("PRINTER", result.getName());
        verify(repo).save(any(AssetType.class));
    }

    @Test
    void delete_NotExist_Throws() {
        when(repo.existsById(15L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> svc.delete(15L));
    }
}
