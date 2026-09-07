package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TrackingEventRepository extends JpaRepository<TrackingEvent, Long> {
    List<TrackingEvent> findAllByIsActiveTrue();
    Optional<TrackingEvent> findByIdAndIsActiveTrue(Long id);
}
