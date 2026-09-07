package com.example.logisticsnetwork.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InvoiceRequest {
 @NotNull @Positive private BigDecimal amount;
 @NotBlank @Size(max=40) private String status;
 @NotNull @PastOrPresent private LocalDate issuedDate;
 @NotNull @Positive private Long shipmentId;
 @NotNull @Positive private Long customerId;
}