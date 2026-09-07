package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findAllByIsActiveTrue();
    Optional<Driver> findByIdAndIsActiveTrue(Long id);
    long countByCarrierIdAndIsActiveTrue(Long carrierId);
}