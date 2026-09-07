package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.ShipmentItemDTO;
import com.example.logisticsnetwork.dto.request.ShipmentItemRequest;
import com.example.logisticsnetwork.service.ShipmentItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment-items")
public class ShipmentItemController {

    private final ShipmentItemService service;

    public ShipmentItemController(ShipmentItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ShipmentItemDTO> create(@Valid @RequestBody ShipmentItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ShipmentItemDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<ShipmentItemDTO> getAll() {
        return ShipmentItemDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public ShipmentItemDTO getById(@PathVariable Long id) {
        return ShipmentItemDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public ShipmentItemDTO update(@PathVariable Long id, @Valid @RequestBody ShipmentItemRequest request) {
        return ShipmentItemDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
