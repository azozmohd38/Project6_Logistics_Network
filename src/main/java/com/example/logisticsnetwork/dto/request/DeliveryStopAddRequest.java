package com.example.logisticsnetwork.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryStopAddRequest {
    @NotNull @Positive private Long routeId;
    @NotNull @Positive private Long shipmentId;
    @NotNull @Positive private Integer sequence;
    @NotBlank @Size(max=200) private String address;
    @FutureOrPresent private LocalDateTime eta;
}
