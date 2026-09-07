package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByIsActiveTrue();
    Optional<Route> findByIdAndIsActiveTrue(Long id);
}
