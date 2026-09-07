package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.CustomerRequest;
import com.example.logisticsnetwork.entity.Customer;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CustomerService {
 private final CustomerRepository repository;
 public CustomerService(CustomerRepository repository){this.repository=repository;}
 public Customer create(CustomerRequest r){Customer e=new Customer();apply(e,r);return repository.save(e);}
 public List<Customer> getAll(){return repository.findAllByIsActiveTrue();}
 public Customer getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Customer not found: "+id));}
 public Customer update(Long id,CustomerRequest r){Customer e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Customer e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(Customer e,CustomerRequest r){e.setName(r.getName());e.setEmail(r.getEmail());e.setPhoneNumber(r.getPhoneNumber());e.setType(r.getType());}
}