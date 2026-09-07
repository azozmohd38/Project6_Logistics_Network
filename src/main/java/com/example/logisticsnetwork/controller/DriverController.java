package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.DriverDTO;
import com.example.logisticsnetwork.dto.request.DriverRequest;
import com.example.logisticsnetwork.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DriverDTO> create(@Valid @RequestBody DriverRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DriverDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<DriverDTO> getAll() {
        return DriverDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public DriverDTO getById(@PathVariable Long id) {
        return DriverDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public DriverDTO update(@PathVariable Long id, @Valid @RequestBody DriverRequest request) {
        return DriverDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
