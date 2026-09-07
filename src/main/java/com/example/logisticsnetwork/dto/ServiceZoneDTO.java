package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.ServiceZone;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ServiceZoneDTO {
 private Long id; private String name; private String region; private BigDecimal baseRate;
 public static ServiceZoneDTO convertToDTO(ServiceZone e){return ServiceZoneDTO.builder().id(e.getId()).name(e.getName()).region(e.getRegion()).baseRate(e.getBaseRate()).build();}
 public static List<ServiceZoneDTO> convertToDTO(List<ServiceZone> list){return list.stream().map(ServiceZoneDTO::convertToDTO).toList();}
}