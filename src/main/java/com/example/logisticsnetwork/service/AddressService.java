package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.AddressRequest;
import com.example.logisticsnetwork.entity.Address;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AddressService {
 private final AddressRepository repository; private final CustomerService customerService;
 public AddressService(AddressRepository repository,CustomerService customerService){this.repository=repository;this.customerService=customerService;}
 public Address create(AddressRequest r){Address e=new Address();apply(e,r);return repository.save(e);}
 public List<Address> getAll(){return repository.findAllByIsActiveTrue();}
 public Address getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Address not found: "+id));}
 public Address update(Long id,AddressRequest r){Address e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Address e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(Address e,AddressRequest r){e.setStreet(r.getStreet());e.setCity(r.getCity());e.setPostalCode(r.getPostalCode());e.setCountry(r.getCountry());e.setCustomer(customerService.getById(r.getCustomerId()));}
}