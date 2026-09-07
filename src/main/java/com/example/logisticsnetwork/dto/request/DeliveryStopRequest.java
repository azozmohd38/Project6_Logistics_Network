package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DeliveryStopRequest {
 @NotNull @Positive private Integer sequence;
 @NotBlank @Size(max=200) private String address;
 @NotBlank @Size(max=40) private String status;
 @FutureOrPresent private LocalDateTime eta;
 @NotNull @Positive private Long routeId;
 @NotNull @Positive private Long shipmentId;
}