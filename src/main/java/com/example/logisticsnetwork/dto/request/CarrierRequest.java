package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CarrierRequest {
 @NotBlank @Size(max=120) private String name;
 @Email @Size(max=150) private String contactEmail;
 @Size(max=30) private String phoneNumber;
 @Size(max=100) private String country;
}