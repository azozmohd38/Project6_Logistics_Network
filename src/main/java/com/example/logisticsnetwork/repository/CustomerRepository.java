package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByIsActiveTrue();
    Optional<Customer> findByIdAndIsActiveTrue(Long id);
}
