package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Shipment;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ShipmentDTO {
 private Long id; private LocalDate shipmentDate; private String status; private Double totalWeight; private Long warehouseId; private Long customerId; private Long carrierId;
 public static ShipmentDTO convertToDTO(Shipment e){return ShipmentDTO.builder().id(e.getId()).shipmentDate(e.getShipmentDate()).status(e.getStatus()).totalWeight(e.getTotalWeight()).warehouseId(e.getWarehouse().getId()).customerId(e.getCustomer().getId()).carrierId(e.getCarrier()==null?null:e.getCarrier().getId()).build();}
 public static List<ShipmentDTO> convertToDTO(List<Shipment> list){return list.stream().map(ShipmentDTO::convertToDTO).toList();}
}