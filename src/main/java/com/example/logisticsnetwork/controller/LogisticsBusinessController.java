package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.*;
import com.example.logisticsnetwork.dto.request.*;
import com.example.logisticsnetwork.service.LogisticsBusinessService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/business")
public class LogisticsBusinessController {

    private final LogisticsBusinessService service;

    public LogisticsBusinessController(LogisticsBusinessService service) {
        this.service = service;
    }

    @PostMapping("/shipments")
    public ResponseEntity<ShipmentDTO> createShipment(@Valid @RequestBody ShipmentCreateBusinessRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ShipmentDTO.convertToDTO(service.createShipment(request)));
    }

    @PutMapping("/shipments/{shipmentId}/assign")
    public ShipmentDTO assignShipment(
            @PathVariable Long shipmentId,
            @Valid @RequestBody AssignShipmentRequest request) {
        return ShipmentDTO.convertToDTO(service.assignShipment(shipmentId, request));
    }

    @PostMapping("/routes")
    public ResponseEntity<RouteDTO> buildRoute(@Valid @RequestBody RouteBuildRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RouteDTO.convertToDTO(service.buildRoute(request)));
    }

    @PostMapping("/delivery-stops")
    public ResponseEntity<DeliveryStopDTO> addDeliveryStop(@Valid @RequestBody DeliveryStopAddRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DeliveryStopDTO.convertToDTO(service.addDeliveryStop(request)));
    }

    @PostMapping("/tracking-events")
    public ResponseEntity<TrackingEventDTO> appendTrackingEvent(@Valid @RequestBody TrackingAppendRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TrackingEventDTO.convertToDTO(service.appendTrackingEvent(request)));
    }

    @PutMapping("/delivery-stops/{stopId}/complete")
    public DeliveryStopDTO completeDeliveryStop(@PathVariable Long stopId) {
        return DeliveryStopDTO.convertToDTO(service.completeDeliveryStop(stopId));
    }

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceDTO> generateInvoice(@Valid @RequestBody InvoiceGenerateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InvoiceDTO.convertToDTO(service.generateInvoice(request)));
    }
}
