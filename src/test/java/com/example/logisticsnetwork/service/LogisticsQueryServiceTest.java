package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.entity.Vehicle;
import com.example.logisticsnetwork.repository.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogisticsQueryServiceTest {

    @Test
    void availableVehiclesDelegatesToRepositoryQuery() {
        ShipmentRepository shipmentRepository = mock(ShipmentRepository.class);
        InventoryItemRepository inventoryItemRepository = mock(InventoryItemRepository.class);
        RouteRepository routeRepository = mock(RouteRepository.class);
        VehicleRepository vehicleRepository = mock(VehicleRepository.class);
        InvoiceRepository invoiceRepository = mock(InvoiceRepository.class);

        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.findCurrentlyAvailable()).thenReturn(List.of(vehicle));

        LogisticsQueryService service = new LogisticsQueryService(
                shipmentRepository, inventoryItemRepository, routeRepository, vehicleRepository, invoiceRepository);

        assertEquals(1, service.availableVehicles().size());
        verify(vehicleRepository).findCurrentlyAvailable();
    }
}
