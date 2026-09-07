package com.example.logisticsnetwork.dto.request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ShipmentCreateBusinessRequest {
 @NotNull @Positive private Long warehouseId;
 @NotNull @Positive private Long customerId;
 @NotNull @PastOrPresent private LocalDate shipmentDate;
 @NotEmpty private List<@Valid ShipmentLineRequest> items;
}