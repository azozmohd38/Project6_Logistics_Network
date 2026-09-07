package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Warehouse;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class WarehouseDTO {
 private Long id; private String name; private String location; private Integer capacity;
 public static WarehouseDTO convertToDTO(Warehouse e){return WarehouseDTO.builder().id(e.getId()).name(e.getName()).location(e.getLocation()).capacity(e.getCapacity()).build();}
 public static List<WarehouseDTO> convertToDTO(List<Warehouse> list){return list.stream().map(WarehouseDTO::convertToDTO).toList();}
}