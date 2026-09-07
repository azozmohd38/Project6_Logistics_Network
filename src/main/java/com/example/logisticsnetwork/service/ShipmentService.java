package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.ShipmentRequest;
import com.example.logisticsnetwork.entity.Shipment;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ShipmentService {
 private final ShipmentRepository repository; private final WarehouseService warehouseService; private final CustomerService customerService; private final CarrierService carrierService;
 public ShipmentService(ShipmentRepository repository,WarehouseService warehouseService,CustomerService customerService,CarrierService carrierService){this.repository=repository;this.warehouseService=warehouseService;this.customerService=customerService;this.carrierService=carrierService;}
 public Shipment create(ShipmentRequest r){Shipment e=new Shipment();apply(e,r);return repository.save(e);}
 public List<Shipment> getAll(){return repository.findAllByIsActiveTrue();}
 public Shipment getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Shipment not found: "+id));}
 public Shipment update(Long id,ShipmentRequest r){Shipment e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){Shipment e=getById(id);e.setIsActive(false);repository.save(e);}
 public Shipment save(Shipment e){return repository.save(e);}
 private void apply(Shipment e,ShipmentRequest r){e.setShipmentDate(r.getShipmentDate());e.setStatus(r.getStatus());e.setTotalWeight(r.getTotalWeight());e.setWarehouse(warehouseService.getById(r.getWarehouseId()));e.setCustomer(customerService.getById(r.getCustomerId()));e.setCarrier(r.getCarrierId()==null?null:carrierService.getById(r.getCarrierId()));}
}