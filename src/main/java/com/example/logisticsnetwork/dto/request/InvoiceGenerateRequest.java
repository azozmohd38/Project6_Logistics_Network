package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InvoiceGenerateRequest {
 @NotNull @Positive private Long shipmentId;
 @NotNull @Positive private BigDecimal amount;
}