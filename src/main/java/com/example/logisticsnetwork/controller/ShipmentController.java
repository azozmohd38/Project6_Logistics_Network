package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.ShipmentDTO;
import com.example.logisticsnetwork.dto.request.ShipmentRequest;
import com.example.logisticsnetwork.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService service;

    public ShipmentController(ShipmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ShipmentDTO> create(@Valid @RequestBody ShipmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ShipmentDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<ShipmentDTO> getAll() {
        return ShipmentDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public ShipmentDTO getById(@PathVariable Long id) {
        return ShipmentDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public ShipmentDTO update(@PathVariable Long id, @Valid @RequestBody ShipmentRequest request) {
        return ShipmentDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
