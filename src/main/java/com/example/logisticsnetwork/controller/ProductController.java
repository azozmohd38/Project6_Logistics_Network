package com.example.logisticsnetwork.controller;

import com.example.logisticsnetwork.dto.ProductDTO;
import com.example.logisticsnetwork.dto.request.ProductRequest;
import com.example.logisticsnetwork.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProductDTO.convertToDTO(service.create(request)));
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return ProductDTO.convertToDTO(service.getAll());
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return ProductDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ProductDTO.convertToDTO(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
