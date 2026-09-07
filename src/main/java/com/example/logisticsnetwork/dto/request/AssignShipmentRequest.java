package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AssignShipmentRequest {
 @NotNull @Positive private Long carrierId;
 @NotNull @Positive private Long vehicleId;
 @NotNull @Positive private Long driverId;
}