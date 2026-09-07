package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByIsActiveTrue();
    Optional<Invoice> findByIdAndIsActiveTrue(Long id);
}
