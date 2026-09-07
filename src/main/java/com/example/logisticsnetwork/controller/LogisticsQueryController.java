package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.*;
import com.example.logisticsnetwork.service.LogisticsQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/queries")
public class LogisticsQueryController {

    private final LogisticsQueryService service;

    public LogisticsQueryController(LogisticsQueryService service) {
        this.service = service;
    }

    @GetMapping("/shipments/by-status")
    public List<ShipmentDTO> shipmentsByStatus(@RequestParam String status) {
        return ShipmentDTO.convertToDTO(service.shipmentsByStatus(status));
    }

    @GetMapping("/inventory/below-threshold")
    public List<InventoryItemDTO> inventoryBelowThreshold(@RequestParam Integer threshold) {
        return InventoryItemDTO.convertToDTO(service.inventoryBelowThreshold(threshold));
    }

    @GetMapping("/routes/by-driver-date")
    public List<RouteDTO> routesForDriverOnDate(
            @RequestParam Long driverId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate routeDate) {
        return RouteDTO.convertToDTO(service.routesForDriverOnDate(driverId, routeDate));
    }

    @GetMapping("/vehicles/available")
    public List<VehicleDTO> availableVehicles() {
        return VehicleDTO.convertToDTO(service.availableVehicles());
    }

    @GetMapping("/customers/{customerId}/shipment-history")
    public List<ShipmentDTO> customerShipmentHistory(@PathVariable Long customerId) {
        return ShipmentDTO.convertToDTO(service.customerShipmentHistory(customerId));
    }

    @GetMapping("/customers/{customerId}/unpaid-invoices")
    public List<InvoiceDTO> unpaidInvoicesForCustomer(@PathVariable Long customerId) {
        return InvoiceDTO.convertToDTO(service.unpaidInvoicesForCustomer(customerId));
    }
}
