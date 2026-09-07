package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.CustomerDTO;
import com.example.logisticsnetwork.dto.request.CustomerRequest;
import com.example.logisticsnetwork.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CustomerDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<CustomerDTO> getAll() {
        return CustomerDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable Long id) {
        return CustomerDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        return CustomerDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
