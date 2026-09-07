package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.AddressDTO;
import com.example.logisticsnetwork.dto.request.AddressRequest;
import com.example.logisticsnetwork.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AddressDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<AddressDTO> getAll() {
        return AddressDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public AddressDTO getById(@PathVariable Long id) {
        return AddressDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public AddressDTO update(@PathVariable Long id, @Valid @RequestBody AddressRequest request) {
        return AddressDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
