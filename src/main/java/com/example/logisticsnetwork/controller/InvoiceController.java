package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.InvoiceDTO;
import com.example.logisticsnetwork.dto.request.InvoiceRequest;
import com.example.logisticsnetwork.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> create(@Valid @RequestBody InvoiceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InvoiceDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<InvoiceDTO> getAll() {
        return InvoiceDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public InvoiceDTO getById(@PathVariable Long id) {
        return InvoiceDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public InvoiceDTO update(@PathVariable Long id, @Valid @RequestBody InvoiceRequest request) {
        return InvoiceDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
