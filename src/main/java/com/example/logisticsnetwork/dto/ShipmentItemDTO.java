package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.ShipmentItem;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ShipmentItemDTO {
 private Long id; private Integer quantity; private Long shipmentId; private Long productId;
 public static ShipmentItemDTO convertToDTO(ShipmentItem e){return ShipmentItemDTO.builder().id(e.getId()).quantity(e.getQuantity()).shipmentId(e.getShipment().getId()).productId(e.getProduct().getId()).build();}
 public static List<ShipmentItemDTO> convertToDTO(List<ShipmentItem> list){return list.stream().map(ShipmentItemDTO::convertToDTO).toList();}
}