package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DriverRequest {
 @NotBlank @Size(max=120) private String name;
 @NotBlank @Size(max=80) private String licenseNumber;
 @Size(max=30) private String phoneNumber;
 @NotBlank @Size(max=40) private String status;
 @NotNull @Positive private Long carrierId;
}