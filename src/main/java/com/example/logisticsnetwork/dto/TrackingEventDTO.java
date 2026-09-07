package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.TrackingEvent;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class TrackingEventDTO {
 private Long id; private LocalDateTime eventTime; private String location; private String status; private String note; private Long shipmentId;
 public static TrackingEventDTO convertToDTO(TrackingEvent e){return TrackingEventDTO.builder().id(e.getId()).eventTime(e.getEventTime()).location(e.getLocation()).status(e.getStatus()).note(e.getNote()).shipmentId(e.getShipment().getId()).build();}
 public static List<TrackingEventDTO> convertToDTO(List<TrackingEvent> list){return list.stream().map(TrackingEventDTO::convertToDTO).toList();}
}