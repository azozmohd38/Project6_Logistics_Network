package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByIsActiveTrue();
    Optional<Address> findByIdAndIsActiveTrue(Long id);
}
