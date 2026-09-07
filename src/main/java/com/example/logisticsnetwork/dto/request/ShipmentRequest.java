package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ShipmentRequest {
 @NotNull @PastOrPresent private LocalDate shipmentDate;
 @NotBlank @Size(max=50) private String status;
 @NotNull @PositiveOrZero private Double totalWeight;
 @NotNull @Positive private Long warehouseId;
 @NotNull @Positive private Long customerId;
 @Positive private Long carrierId;
}