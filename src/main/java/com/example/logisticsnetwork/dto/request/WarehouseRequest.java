package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class WarehouseRequest {
 @NotBlank @Size(max=100) private String name;
 @NotBlank @Size(max=200) private String location;
 @NotNull @Positive private Integer capacity;
}