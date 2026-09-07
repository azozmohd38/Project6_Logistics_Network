package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.InventoryItemDTO;
import com.example.logisticsnetwork.dto.request.InventoryItemRequest;
import com.example.logisticsnetwork.service.InventoryItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-items")
public class InventoryItemController {

    private final InventoryItemService service;

    public InventoryItemController(InventoryItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InventoryItemDTO> create(@Valid @RequestBody InventoryItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InventoryItemDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<InventoryItemDTO> getAll() {
        return InventoryItemDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public InventoryItemDTO getById(@PathVariable Long id) {
        return InventoryItemDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public InventoryItemDTO update(@PathVariable Long id, @Valid @RequestBody InventoryItemRequest request) {
        return InventoryItemDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
