package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Driver;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class DriverDTO {
 private Long id; private String name; private String status; private Long carrierId;
 public static DriverDTO convertToDTO(Driver e){return DriverDTO.builder().id(e.getId()).name(e.getName()).status(e.getStatus()).carrierId(e.getCarrier().getId()).build();}
 public static List<DriverDTO> convertToDTO(List<Driver> list){return list.stream().map(DriverDTO::convertToDTO).toList();}
}