package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.InvoiceRequest;
import com.example.logisticsnetwork.entity.Invoice;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class InvoiceService {
 private final InvoiceRepository repository; private final ShipmentService shipmentService; private final CustomerService customerService;
 public InvoiceService(InvoiceRepository repository,ShipmentService shipmentService,CustomerService customerService){this.repository=repository;this.shipmentService=shipmentService;this.customerService=customerService;}
 public Invoice create(InvoiceRequest r){Invoice e=new Invoice();apply(e,r);return repository.save(e);}
 public List<Invoice> getAll(){return repository.findAllByIsActiveTrue();}
 public Invoice getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Invoice not found: "+id));}
 public Invoice update(Long id,InvoiceRequest r){Invoice e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Invoice e=getById(id);e.setIsActive(false);repository.save(e);}
 public Invoice save(Invoice e){return repository.save(e);}
 private void apply(Invoice e,InvoiceRequest r){e.setAmount(r.getAmount());e.setStatus(r.getStatus());e.setIssuedDate(r.getIssuedDate());e.setShipment(shipmentService.getById(r.getShipmentId()));e.setCustomer(customerService.getById(r.getCustomerId()));}
}