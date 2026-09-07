package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.*;
import com.example.logisticsnetwork.repository.*;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final ShipmentRepository shipmentRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final RouteRepository routeRepository;
    private final InvoiceRepository invoiceRepository;
    private final WarehouseService warehouseService;
    private final CarrierService carrierService;
    private final CustomerService customerService;

    public StatisticsService(
            ShipmentRepository shipmentRepository,
            InventoryItemRepository inventoryItemRepository,
            VehicleRepository vehicleRepository,
            DriverRepository driverRepository,
            RouteRepository routeRepository,
            InvoiceRepository invoiceRepository,
            WarehouseService warehouseService,
            CarrierService carrierService,
            CustomerService customerService) {
        this.shipmentRepository = shipmentRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.routeRepository = routeRepository;
        this.invoiceRepository = invoiceRepository;
        this.warehouseService = warehouseService;
        this.carrierService = carrierService;
        this.customerService = customerService;
    }

    public WarehouseStatsDTO warehouseStats(Long warehouseId) {
        warehouseService.getById(warehouseId);
        return WarehouseStatsDTO.builder()
                .warehouseId(warehouseId)
                .activeShipments(shipmentRepository.countActiveForWarehouse(warehouseId))
                .totalInventoryUnits(inventoryItemRepository.totalUnitsForWarehouse(warehouseId))
                .build();
    }

    public CarrierStatsDTO carrierStats(Long carrierId) {
        carrierService.getById(carrierId);
        return CarrierStatsDTO.builder()
                .carrierId(carrierId)
                .vehicles(vehicleRepository.countByCarrierIdAndIsActiveTrue(carrierId))
                .drivers(driverRepository.countByCarrierIdAndIsActiveTrue(carrierId))
                .activeRoutes(routeRepository.countActiveForCarrier(carrierId))
                .build();
    }

    public CustomerStatsDTO customerStats(Long customerId) {
        customerService.getById(customerId);
        return CustomerStatsDTO.builder()
                .customerId(customerId)
                .totalInvoicedAmount(invoiceRepository.totalInvoicedForCustomer(customerId))
                .build();
    }
}
