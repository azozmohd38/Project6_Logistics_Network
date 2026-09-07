package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findAllByIsActiveTrue();
    Optional<Shipment> findByIdAndIsActiveTrue(Long id);
}
