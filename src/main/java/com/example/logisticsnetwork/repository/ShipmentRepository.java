package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findAllByIsActiveTrue();
    Optional<Shipment> findByIdAndIsActiveTrue(Long id);

    @Query("select s from Shipment s where s.isActive = true and lower(s.status) = lower(:status)")
    List<Shipment> findActiveByStatus(@Param("status") String status);

    @Query("select s from Shipment s where s.isActive = true and s.customer.id = :customerId order by s.shipmentDate desc")
    List<Shipment> findCustomerHistory(@Param("customerId") Long customerId);

    @Query("select count(s) from Shipment s where s.isActive = true and s.warehouse.id = :warehouseId and lower(s.status) <> 'delivered'")
    long countActiveForWarehouse(@Param("warehouseId") Long warehouseId);
}