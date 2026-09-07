package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findAllByIsActiveTrue();
    Optional<InventoryItem> findByIdAndIsActiveTrue(Long id);

    Optional<InventoryItem> findByWarehouseIdAndProductIdAndIsActiveTrue(Long warehouseId, Long productId);

    @Query("select i from InventoryItem i where i.isActive = true and i.quantity < :threshold order by i.quantity asc")
    List<InventoryItem> findBelowThreshold(@Param("threshold") Integer threshold);

    @Query("select coalesce(sum(i.quantity), 0) from InventoryItem i where i.isActive = true and i.warehouse.id = :warehouseId")
    Long totalUnitsForWarehouse(@Param("warehouseId") Long warehouseId);
}