package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Vehicle;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class VehicleDTO {
 private Long id; private String plateNumber; private String type; private Double capacityKg; private String status; private Long carrierId;
 public static VehicleDTO convertToDTO(Vehicle e){return VehicleDTO.builder().id(e.getId()).plateNumber(e.getPlateNumber()).type(e.getType()).capacityKg(e.getCapacityKg()).status(e.getStatus()).carrierId(e.getCarrier().getId()).build();}
 public static List<VehicleDTO> convertToDTO(List<Vehicle> list){return list.stream().map(VehicleDTO::convertToDTO).toList();}
}