package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.ServiceZoneDTO;
import com.example.logisticsnetwork.dto.request.ServiceZoneRequest;
import com.example.logisticsnetwork.service.ServiceZoneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-zones")
public class ServiceZoneController {

    private final ServiceZoneService service;

    public ServiceZoneController(ServiceZoneService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceZoneDTO> create(@Valid @RequestBody ServiceZoneRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServiceZoneDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<ServiceZoneDTO> getAll() {
        return ServiceZoneDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public ServiceZoneDTO getById(@PathVariable Long id) {
        return ServiceZoneDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public ServiceZoneDTO update(@PathVariable Long id, @Valid @RequestBody ServiceZoneRequest request) {
        return ServiceZoneDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
