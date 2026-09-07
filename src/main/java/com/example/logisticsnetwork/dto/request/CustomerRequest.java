package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CustomerRequest {
 @NotBlank @Size(max=120) private String name;
 @NotBlank @Email @Size(max=150) private String email;
 @Size(max=30) private String phoneNumber;
 @Size(max=50) private String type;
}