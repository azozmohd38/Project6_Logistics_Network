package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RouteBuildRequest {
 @NotNull @Positive private Long shipmentId;
 @NotNull @Positive private Long vehicleId;
 @NotNull @Positive private Long driverId;
 @NotNull @FutureOrPresent private LocalDate routeDate;
 @NotBlank @Size(max=160) private String origin;
 @NotBlank @Size(max=160) private String destination;
}