package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TrackingAppendRequest {
 @NotNull @Positive private Long shipmentId;
 @NotBlank @Size(max=160) private String location;
 @NotBlank @Size(max=50) private String status;
 @Size(max=500) private String note;
}