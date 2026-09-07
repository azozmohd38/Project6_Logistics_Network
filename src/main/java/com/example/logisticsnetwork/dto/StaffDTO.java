package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Staff;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class StaffDTO {
 private Long id; private String name; private String role; private Long warehouseId;
 public static StaffDTO convertToDTO(Staff e){return StaffDTO.builder().id(e.getId()).name(e.getName()).role(e.getRole()).warehouseId(e.getWarehouse().getId()).build();}
 public static List<StaffDTO> convertToDTO(List<Staff> list){return list.stream().map(StaffDTO::convertToDTO).toList();}
}