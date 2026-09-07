package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.InventoryItem;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class InventoryItemDTO {
 private Long id; private Integer quantity; private String shelfLocation; private Long warehouseId; private Long productId;
 public static InventoryItemDTO convertToDTO(InventoryItem e){return InventoryItemDTO.builder().id(e.getId()).quantity(e.getQuantity()).shelfLocation(e.getShelfLocation()).warehouseId(e.getWarehouse().getId()).productId(e.getProduct().getId()).build();}
 public static List<InventoryItemDTO> convertToDTO(List<InventoryItem> list){return list.stream().map(InventoryItemDTO::convertToDTO).toList();}
}