package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByIsActiveTrue();
    Optional<Vehicle> findByIdAndIsActiveTrue(Long id);

    @Query("select v from Vehicle v where v.isActive = true and lower(v.status) = 'available'")
    List<Vehicle> findCurrentlyAvailable();
}