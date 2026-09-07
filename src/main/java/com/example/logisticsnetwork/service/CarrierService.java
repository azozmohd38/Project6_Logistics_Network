package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.CarrierRequest;
import com.example.logisticsnetwork.entity.Carrier;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.CarrierRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CarrierService {
 private final CarrierRepository repository;
 public CarrierService(CarrierRepository repository){this.repository=repository;}
 public Carrier create(CarrierRequest r){Carrier e=new Carrier();apply(e,r);return repository.save(e);}
 public List<Carrier> getAll(){return repository.findAllByIsActiveTrue();}
 public Carrier getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Carrier not found: "+id));}
 public Carrier update(Long id,CarrierRequest r){Carrier e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Carrier e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(Carrier e,CarrierRequest r){e.setName(r.getName());e.setContactEmail(r.getContactEmail());e.setPhoneNumber(r.getPhoneNumber());e.setCountry(r.getCountry());}
}