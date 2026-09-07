package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    List<Carrier> findAllByIsActiveTrue();
    Optional<Carrier> findByIdAndIsActiveTrue(Long id);
}
