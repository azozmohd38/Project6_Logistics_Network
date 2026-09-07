package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AddressRequest {
 @NotBlank @Size(max=180) private String street;
 @NotBlank @Size(max=100) private String city;
 @Size(max=20) private String postalCode;
 @NotBlank @Size(max=100) private String country;
 @NotNull @Positive private Long customerId;
}