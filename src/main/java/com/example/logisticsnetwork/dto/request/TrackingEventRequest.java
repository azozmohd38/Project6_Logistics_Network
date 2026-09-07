package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TrackingEventRequest {
 @NotNull @PastOrPresent private LocalDateTime eventTime;
 @NotBlank @Size(max=160) private String location;
 @NotBlank @Size(max=50) private String status;
 @Size(max=500) private String note;
 @NotNull @Positive private Long shipmentId;
}