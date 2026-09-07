package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InventoryItemRequest {
 @NotNull @PositiveOrZero private Integer quantity;
 @Size(max=100) private String shelfLocation;
 @NotNull @Positive private Long warehouseId;
 @NotNull @Positive private Long productId;
}