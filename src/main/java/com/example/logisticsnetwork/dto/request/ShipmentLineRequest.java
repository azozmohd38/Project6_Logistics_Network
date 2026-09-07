package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ShipmentLineRequest {
 @NotNull @Positive private Long productId;
 @NotNull @Positive private Integer quantity;
}