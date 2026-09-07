package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.request.WarehouseRequest;
import com.example.logisticsnetwork.entity.Warehouse;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarehouseServiceTest {

    @Test
    void createSavesWarehouse() {
        WarehouseRepository repository = mock(WarehouseRepository.class);
        WarehouseService service = new WarehouseService(repository);
        WarehouseRequest request = new WarehouseRequest("Central", "Muscat", 500);
        when(repository.save(any(Warehouse.class))).thenAnswer(inv -> inv.getArgument(0));

        Warehouse saved = service.create(request);

        assertEquals("Central", saved.getName());
        assertEquals("Muscat", saved.getLocation());
        assertEquals(500, saved.getCapacity());
        verify(repository).save(any(Warehouse.class));
    }

    @Test
    void getByIdThrowsWhenInactiveOrMissing() {
        WarehouseRepository repository = mock(WarehouseRepository.class);
        WarehouseService service = new WarehouseService(repository);
        when(repository.findByIdAndIsActiveTrue(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }
}
