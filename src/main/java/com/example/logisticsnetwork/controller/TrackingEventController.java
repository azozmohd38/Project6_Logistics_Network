package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.TrackingEventDTO;
import com.example.logisticsnetwork.dto.request.TrackingEventRequest;
import com.example.logisticsnetwork.service.TrackingEventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking-events")
public class TrackingEventController {

    private final TrackingEventService service;

    public TrackingEventController(TrackingEventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TrackingEventDTO> create(@Valid @RequestBody TrackingEventRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TrackingEventDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<TrackingEventDTO> getAll() {
        return TrackingEventDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public TrackingEventDTO getById(@PathVariable Long id) {
        return TrackingEventDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public TrackingEventDTO update(@PathVariable Long id, @Valid @RequestBody TrackingEventRequest request) {
        return TrackingEventDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
