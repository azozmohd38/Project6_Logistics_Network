package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByIsActiveTrue();
    Optional<Invoice> findByIdAndIsActiveTrue(Long id);

    @Query("select i from Invoice i where i.isActive = true and i.customer.id = :customerId and lower(i.status) = 'unpaid'")
    List<Invoice> findUnpaidForCustomer(@Param("customerId") Long customerId);

    @Query("select coalesce(sum(i.amount), 0) from Invoice i where i.isActive = true and i.customer.id = :customerId")
    BigDecimal totalInvoicedForCustomer(@Param("customerId") Long customerId);

    boolean existsByShipmentIdAndIsActiveTrue(Long shipmentId);
}