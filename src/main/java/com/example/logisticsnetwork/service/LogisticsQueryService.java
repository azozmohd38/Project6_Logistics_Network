package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.entity.*;
import com.example.logisticsnetwork.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogisticsQueryService {

    private final ShipmentRepository shipmentRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final RouteRepository routeRepository;
    private final VehicleRepository vehicleRepository;
    private final InvoiceRepository invoiceRepository;

    public LogisticsQueryService(
            ShipmentRepository shipmentRepository,
            InventoryItemRepository inventoryItemRepository,
            RouteRepository routeRepository,
            VehicleRepository vehicleRepository,
            InvoiceRepository invoiceRepository) {
        this.shipmentRepository = shipmentRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.routeRepository = routeRepository;
        this.vehicleRepository = vehicleRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public List<Shipment> shipmentsByStatus(String status) {
        return shipmentRepository.findActiveByStatus(status);
    }

    public List<InventoryItem> inventoryBelowThreshold(Integer threshold) {
        return inventoryItemRepository.findBelowThreshold(threshold);
    }

    public List<Route> routesForDriverOnDate(Long driverId, LocalDate routeDate) {
        return routeRepository.findForDriverOnDate(driverId, routeDate);
    }

    public List<Vehicle> availableVehicles() {
        return vehicleRepository.findCurrentlyAvailable();
    }

    public List<Shipment> customerShipmentHistory(Long customerId) {
        return shipmentRepository.findCustomerHistory(customerId);
    }

    public List<Invoice> unpaidInvoicesForCustomer(Long customerId) {
        return invoiceRepository.findUnpaidForCustomer(customerId);
    }
}
