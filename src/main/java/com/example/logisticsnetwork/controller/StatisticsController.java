package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.*;
import com.example.logisticsnetwork.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    private final StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping("/warehouses/{warehouseId}")
    public WarehouseStatsDTO warehouseStats(@PathVariable Long warehouseId) {
        return service.warehouseStats(warehouseId);
    }

    @GetMapping("/carriers/{carrierId}")
    public CarrierStatsDTO carrierStats(@PathVariable Long carrierId) {
        return service.carrierStats(carrierId);
    }

    @GetMapping("/customers/{customerId}")
    public CustomerStatsDTO customerStats(@PathVariable Long customerId) {
        return service.customerStats(customerId);
    }
}
