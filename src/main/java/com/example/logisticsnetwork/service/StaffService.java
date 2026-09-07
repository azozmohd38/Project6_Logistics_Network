package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.StaffRequest;
import com.example.logisticsnetwork.entity.Staff;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.StaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StaffService {
 private final StaffRepository repository; private final WarehouseService warehouseService;
 public StaffService(StaffRepository repository,WarehouseService warehouseService){this.repository=repository;this.warehouseService=warehouseService;}
 public Staff create(StaffRequest r){Staff e=new Staff();apply(e,r);return repository.save(e);}
 public List<Staff> getAll(){return repository.findAllByIsActiveTrue();}
 public Staff getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Staff not found: "+id));}
 public Staff update(Long id,StaffRequest r){Staff e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Staff e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(Staff e,StaffRequest r){e.setName(r.getName());e.setRole(r.getRole());e.setPhoneNumber(r.getPhoneNumber());e.setWarehouse(warehouseService.getById(r.getWarehouseId()));}
}