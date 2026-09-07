package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.DeliveryStop;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class DeliveryStopDTO {
 private Long id; private Integer sequence; private String address; private String status; private LocalDateTime eta; private Long routeId; private Long shipmentId;
 public static DeliveryStopDTO convertToDTO(DeliveryStop e){return DeliveryStopDTO.builder().id(e.getId()).sequence(e.getSequence()).address(e.getAddress()).status(e.getStatus()).eta(e.getEta()).routeId(e.getRoute().getId()).shipmentId(e.getShipment().getId()).build();}
 public static List<DeliveryStopDTO> convertToDTO(List<DeliveryStop> list){return list.stream().map(DeliveryStopDTO::convertToDTO).toList();}
}