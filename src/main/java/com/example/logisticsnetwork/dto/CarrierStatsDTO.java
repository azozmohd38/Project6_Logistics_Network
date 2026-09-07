package com.example.logisticsnetwork.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrierStatsDTO {
    private Long carrierId;
    private long vehicles;
    private long drivers;
    private long activeRoutes;
}
