package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.DriverRequest;
import com.example.logisticsnetwork.entity.Driver;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.DriverRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DriverService {
 private final DriverRepository repository; private final CarrierService carrierService;
 public DriverService(DriverRepository repository,CarrierService carrierService){this.repository=repository;this.carrierService=carrierService;}
 public Driver create(DriverRequest r){Driver e=new Driver();apply(e,r);return repository.save(e);}
 public List<Driver> getAll(){return repository.findAllByIsActiveTrue();}
 public Driver getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Driver not found: "+id));}
 public Driver update(Long id,DriverRequest r){Driver e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Driver e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(Driver e,DriverRequest r){e.setName(r.getName());e.setLicenseNumber(r.getLicenseNumber());e.setPhoneNumber(r.getPhoneNumber());e.setStatus(r.getStatus());e.setCarrier(carrierService.getById(r.getCarrierId()));}
}