package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.DeliveryStop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DeliveryStopRepository extends JpaRepository<DeliveryStop, Long> {
    List<DeliveryStop> findAllByIsActiveTrue();
    Optional<DeliveryStop> findByIdAndIsActiveTrue(Long id);
}
