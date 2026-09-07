package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RouteRequest {
 @NotNull @FutureOrPresent private LocalDate routeDate;
 @NotBlank @Size(max=160) private String origin;
 @NotBlank @Size(max=160) private String destination;
 @NotBlank @Size(max=40) private String status;
 @NotNull @Positive private Long vehicleId;
 @NotNull @Positive private Long driverId;
}