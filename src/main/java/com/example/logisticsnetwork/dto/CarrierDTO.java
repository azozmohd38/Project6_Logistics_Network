package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Carrier;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class CarrierDTO {
 private Long id; private String name; private String country;
 public static CarrierDTO convertToDTO(Carrier e){return CarrierDTO.builder().id(e.getId()).name(e.getName()).country(e.getCountry()).build();}
 public static List<CarrierDTO> convertToDTO(List<Carrier> list){return list.stream().map(CarrierDTO::convertToDTO).toList();}
}