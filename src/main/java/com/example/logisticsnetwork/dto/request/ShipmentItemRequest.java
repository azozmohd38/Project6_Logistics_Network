package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ShipmentItemRequest {
 @NotNull @Positive private Integer quantity;
 @NotNull @Positive private Long shipmentId;
 @NotNull @Positive private Long productId;
}