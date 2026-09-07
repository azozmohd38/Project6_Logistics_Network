package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.WarehouseRequest;
import com.example.logisticsnetwork.entity.Warehouse;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class WarehouseService {
 private final WarehouseRepository repository;
 public WarehouseService(WarehouseRepository repository){this.repository=repository;}
 public Warehouse create(WarehouseRequest r){Warehouse e=new Warehouse(); apply(e,r); return repository.save(e);}
 public List<Warehouse> getAll(){return repository.findAllByIsActiveTrue();}
 public Warehouse getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Warehouse not found: "+id));}
 public Warehouse update(Long id,WarehouseRequest r){Warehouse e=getById(id); apply(e,r); return repository.save(e);}
 public void delete(Long id){Warehouse e=getById(id); e.setIsActive(false); repository.save(e);}
 private void apply(Warehouse e,WarehouseRequest r){e.setName(r.getName());e.setLocation(r.getLocation());e.setCapacity(r.getCapacity());}
}