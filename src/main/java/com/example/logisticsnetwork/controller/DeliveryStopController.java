package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.DeliveryStopDTO;
import com.example.logisticsnetwork.dto.request.DeliveryStopRequest;
import com.example.logisticsnetwork.service.DeliveryStopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-stops")
public class DeliveryStopController {

    private final DeliveryStopService service;

    public DeliveryStopController(DeliveryStopService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeliveryStopDTO> create(@Valid @RequestBody DeliveryStopRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DeliveryStopDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<DeliveryStopDTO> getAll() {
        return DeliveryStopDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public DeliveryStopDTO getById(@PathVariable Long id) {
        return DeliveryStopDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public DeliveryStopDTO update(@PathVariable Long id, @Valid @RequestBody DeliveryStopRequest request) {
        return DeliveryStopDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
