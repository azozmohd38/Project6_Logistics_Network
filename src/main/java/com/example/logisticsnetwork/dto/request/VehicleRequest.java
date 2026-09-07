package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VehicleRequest {
 @NotBlank @Size(max=40) private String plateNumber;
 @NotBlank @Size(max=60) private String type;
 @NotNull @Positive private Double capacityKg;
 @NotBlank @Size(max=40) private String status;
 @NotNull @Positive private Long carrierId;
}