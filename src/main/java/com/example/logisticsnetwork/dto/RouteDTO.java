package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Route;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class RouteDTO {
 private Long id; private LocalDate routeDate; private String origin; private String destination; private String status; private Long vehicleId; private Long driverId;
 public static RouteDTO convertToDTO(Route e){return RouteDTO.builder().id(e.getId()).routeDate(e.getRouteDate()).origin(e.getOrigin()).destination(e.getDestination()).status(e.getStatus()).vehicleId(e.getVehicle().getId()).driverId(e.getDriver().getId()).build();}
 public static List<RouteDTO> convertToDTO(List<Route> list){return list.stream().map(RouteDTO::convertToDTO).toList();}
}