package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.StaffDTO;
import com.example.logisticsnetwork.dto.request.StaffRequest;
import com.example.logisticsnetwork.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StaffDTO> create(@Valid @RequestBody StaffRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StaffDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<StaffDTO> getAll() {
        return StaffDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public StaffDTO getById(@PathVariable Long id) {
        return StaffDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public StaffDTO update(@PathVariable Long id, @Valid @RequestBody StaffRequest request) {
        return StaffDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
