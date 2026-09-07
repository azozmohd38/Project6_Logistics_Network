package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ServiceZoneRequest {
 @NotBlank @Size(max=100) private String name;
 @NotBlank @Size(max=120) private String region;
 @NotNull @PositiveOrZero private BigDecimal baseRate;
}