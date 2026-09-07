package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.WarehouseStatsDTO;
import com.example.logisticsnetwork.entity.Warehouse;
import com.example.logisticsnetwork.repository.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatisticsServiceTest {

    @Test
    void warehouseStatsAggregatesRequiredValues() {
        ShipmentRepository shipments = mock(ShipmentRepository.class);
        InventoryItemRepository inventory = mock(InventoryItemRepository.class);
        VehicleRepository vehicles = mock(VehicleRepository.class);
        DriverRepository drivers = mock(DriverRepository.class);
        RouteRepository routes = mock(RouteRepository.class);
        InvoiceRepository invoices = mock(InvoiceRepository.class);
        WarehouseService warehouses = mock(WarehouseService.class);
        CarrierService carriers = mock(CarrierService.class);
        CustomerService customers = mock(CustomerService.class);

        when(warehouses.getById(1L)).thenReturn(new Warehouse());
        when(shipments.countActiveForWarehouse(1L)).thenReturn(3L);
        when(inventory.totalUnitsForWarehouse(1L)).thenReturn(125L);

        StatisticsService service = new StatisticsService(
                shipments, inventory, vehicles, drivers, routes, invoices, warehouses, carriers, customers);

        WarehouseStatsDTO stats = service.warehouseStats(1L);

        assertEquals(3L, stats.getActiveShipments());
        assertEquals(125L, stats.getTotalInventoryUnits());
    }
}
