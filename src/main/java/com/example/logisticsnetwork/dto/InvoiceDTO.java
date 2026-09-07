package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Invoice;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class InvoiceDTO {
 private Long id; private BigDecimal amount; private String status; private LocalDate issuedDate; private Long shipmentId; private Long customerId;
 public static InvoiceDTO convertToDTO(Invoice e){return InvoiceDTO.builder().id(e.getId()).amount(e.getAmount()).status(e.getStatus()).issuedDate(e.getIssuedDate()).shipmentId(e.getShipment().getId()).customerId(e.getCustomer().getId()).build();}
 public static List<InvoiceDTO> convertToDTO(List<Invoice> list){return list.stream().map(InvoiceDTO::convertToDTO).toList();}
}