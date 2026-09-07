package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.CarrierDTO;
import com.example.logisticsnetwork.dto.request.CarrierRequest;
import com.example.logisticsnetwork.service.CarrierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carriers")
public class CarrierController {

    private final CarrierService service;

    public CarrierController(CarrierService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarrierDTO> create(@Valid @RequestBody CarrierRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CarrierDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<CarrierDTO> getAll() {
        return CarrierDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public CarrierDTO getById(@PathVariable Long id) {
        return CarrierDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public CarrierDTO update(@PathVariable Long id, @Valid @RequestBody CarrierRequest request) {
        return CarrierDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
