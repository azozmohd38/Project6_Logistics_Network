package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.ServiceZoneRequest;
import com.example.logisticsnetwork.entity.ServiceZone;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.ServiceZoneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ServiceZoneService {
 private final ServiceZoneRepository repository;
 public ServiceZoneService(ServiceZoneRepository repository){this.repository=repository;}
 public ServiceZone create(ServiceZoneRequest r){ServiceZone e=new ServiceZone();apply(e,r);return repository.save(e);}
 public List<ServiceZone> getAll(){return repository.findAllByIsActiveTrue();}
 public ServiceZone getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("ServiceZone not found: "+id));}
 public ServiceZone update(Long id,ServiceZoneRequest r){ServiceZone e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){ServiceZone e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(ServiceZone e,ServiceZoneRequest r){e.setName(r.getName());e.setRegion(r.getRegion());e.setBaseRate(r.getBaseRate());}
}