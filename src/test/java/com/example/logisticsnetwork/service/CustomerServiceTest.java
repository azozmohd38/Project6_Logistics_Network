package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.request.CustomerRequest;
import com.example.logisticsnetwork.entity.Customer;
import com.example.logisticsnetwork.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Test
    void createMapsCustomerRequest() {
        CustomerRepository repository = mock(CustomerRepository.class);
        CustomerService service = new CustomerService(repository);
        CustomerRequest request = new CustomerRequest("Acme", "ops@acme.com", "90000000", "BUSINESS");
        when(repository.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        Customer customer = service.create(request);

        assertEquals("Acme", customer.getName());
        assertEquals("ops@acme.com", customer.getEmail());
        assertEquals("BUSINESS", customer.getType());
    }
}
