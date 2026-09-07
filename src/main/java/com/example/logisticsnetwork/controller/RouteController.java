package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.RouteDTO;
import com.example.logisticsnetwork.dto.request.RouteRequest;
import com.example.logisticsnetwork.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RouteDTO> create(@Valid @RequestBody RouteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RouteDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<RouteDTO> getAll() {
        return RouteDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public RouteDTO getById(@PathVariable Long id) {
        return RouteDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public RouteDTO update(@PathVariable Long id, @Valid @RequestBody RouteRequest request) {
        return RouteDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
