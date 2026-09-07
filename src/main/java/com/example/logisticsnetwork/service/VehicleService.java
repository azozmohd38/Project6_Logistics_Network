package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.VehicleRequest;
import com.example.logisticsnetwork.entity.Vehicle;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class VehicleService {
 private final VehicleRepository repository; private final CarrierService carrierService;
 public VehicleService(VehicleRepository repository,CarrierService carrierService){this.repository=repository;this.carrierService=carrierService;}
 public Vehicle create(VehicleRequest r){Vehicle e=new Vehicle();apply(e,r);return repository.save(e);}
 public List<Vehicle> getAll(){return repository.findAllByIsActiveTrue();}
 public Vehicle getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Vehicle not found: "+id));}
 public Vehicle update(Long id,VehicleRequest r){Vehicle e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Vehicle e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(Vehicle e,VehicleRequest r){e.setPlateNumber(r.getPlateNumber());e.setType(r.getType());e.setCapacityKg(r.getCapacityKg());e.setStatus(r.getStatus());e.setCarrier(carrierService.getById(r.getCarrierId()));}
}