package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StaffRequest {
 @NotBlank @Size(max=120) private String name;
 @NotBlank @Size(max=80) private String role;
 @Size(max=30) private String phoneNumber;
 @NotNull @Positive private Long warehouseId;
}