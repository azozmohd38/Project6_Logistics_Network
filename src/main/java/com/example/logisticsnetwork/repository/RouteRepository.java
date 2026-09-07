package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByIsActiveTrue();
    Optional<Route> findByIdAndIsActiveTrue(Long id);

    @Query("select r from Route r where r.isActive = true and r.driver.id = :driverId and r.routeDate = :routeDate")
    List<Route> findForDriverOnDate(@Param("driverId") Long driverId, @Param("routeDate") LocalDate routeDate);

    @Query("select count(r) from Route r where r.isActive = true and r.driver.carrier.id = :carrierId and lower(r.status) <> 'complete'")
    long countActiveForCarrier(@Param("carrierId") Long carrierId);
}