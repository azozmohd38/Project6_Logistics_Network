package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.WarehouseDTO;
import com.example.logisticsnetwork.dto.request.WarehouseRequest;
import com.example.logisticsnetwork.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> create(@Valid @RequestBody WarehouseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WarehouseDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<WarehouseDTO> getAll() {
        return WarehouseDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public WarehouseDTO getById(@PathVariable Long id) {
        return WarehouseDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public WarehouseDTO update(@PathVariable Long id, @Valid @RequestBody WarehouseRequest request) {
        return WarehouseDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
