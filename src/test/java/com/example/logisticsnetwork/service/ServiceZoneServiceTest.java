package com.example.logisticsnetwork.service;

import com.example.logisticsnetwork.dto.request.ServiceZoneRequest;
import com.example.logisticsnetwork.entity.ServiceZone;
import com.example.logisticsnetwork.repository.ServiceZoneRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceZoneServiceTest {

    @Test
    void createMapsZoneRate() {
        ServiceZoneRepository repository = mock(ServiceZoneRepository.class);
        ServiceZoneService service = new ServiceZoneService(repository);
        ServiceZoneRequest request = new ServiceZoneRequest("North", "Al Batinah", new BigDecimal("25.00"));
        when(repository.save(any(ServiceZone.class))).thenAnswer(inv -> inv.getArgument(0));

        ServiceZone zone = service.create(request);

        assertEquals("North", zone.getName());
        assertEquals(new BigDecimal("25.00"), zone.getBaseRate());
    }
}
