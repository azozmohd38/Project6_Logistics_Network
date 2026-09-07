package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.VehicleDTO;
import com.example.logisticsnetwork.dto.request.VehicleRequest;
import com.example.logisticsnetwork.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> create(@Valid @RequestBody VehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VehicleDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<VehicleDTO> getAll() {
        return VehicleDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public VehicleDTO getById(@PathVariable Long id) {
        return VehicleDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public VehicleDTO update(@PathVariable Long id, @Valid @RequestBody VehicleRequest request) {
        return VehicleDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
