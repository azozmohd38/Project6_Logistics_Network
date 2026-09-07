package com.example.logisticsnetwork.repository;

import com.example.logisticsnetwork.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findAllByIsActiveTrue();
    Optional<Staff> findByIdAndIsActiveTrue(Long id);
}
