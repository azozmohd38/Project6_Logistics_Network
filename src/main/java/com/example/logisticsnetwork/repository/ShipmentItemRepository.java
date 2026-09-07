package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {
    List<ShipmentItem> findAllByIsActiveTrue();
    Optional<ShipmentItem> findByIdAndIsActiveTrue(Long id);
}
