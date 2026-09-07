package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findAllByIsActiveTrue();
    Optional<InventoryItem> findByIdAndIsActiveTrue(Long id);
}
