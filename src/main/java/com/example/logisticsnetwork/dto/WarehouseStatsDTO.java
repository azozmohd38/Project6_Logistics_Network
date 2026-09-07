package com.example.logisticsnetwork.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseStatsDTO {
    private Long warehouseId;
    private long activeShipments;
    private long totalInventoryUnits;
}
