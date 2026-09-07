package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
 @NotBlank @Size(max=120) private String name;
 @NotBlank @Size(max=80) private String sku;
 @NotNull @Positive private Double weightKg;
 @Size(max=80) private String category;
}