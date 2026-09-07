package com.example.logisticsnetwork.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatsDTO {
    private Long customerId;
    private BigDecimal totalInvoicedAmount;
}
