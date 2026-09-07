package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.request.ProductRequest;
import com.example.logisticsnetwork.entity.Product;
import com.example.logisticsnetwork.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void createMapsProductRequest() {
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);
        ProductRequest request = new ProductRequest("Box", "BOX-1", 2.0, "Packaging");
        when(repository.save(any(Product.class))).thenAnswer(inv -> inv.getArgument(0));

        Product product = service.create(request);

        assertEquals("Box", product.getName());
        assertEquals("BOX-1", product.getSku());
        assertEquals(2.0, product.getWeightKg());
        assertEquals("Packaging", product.getCategory());
    }
}
