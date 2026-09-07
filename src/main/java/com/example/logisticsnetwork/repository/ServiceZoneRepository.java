package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.ServiceZone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ServiceZoneRepository extends JpaRepository<ServiceZone, Long> {
    List<ServiceZone> findAllByIsActiveTrue();
    Optional<ServiceZone> findByIdAndIsActiveTrue(Long id);
}
