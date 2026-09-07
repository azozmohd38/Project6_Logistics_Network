package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.ShipmentItemRequest;
import com.example.logisticsnetwork.entity.ShipmentItem;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.ShipmentItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ShipmentItemService {
 private final ShipmentItemRepository repository; private final ShipmentService shipmentService; private final ProductService productService;
 public ShipmentItemService(ShipmentItemRepository repository,ShipmentService shipmentService,ProductService productService){this.repository=repository;this.shipmentService=shipmentService;this.productService=productService;}
 public ShipmentItem create(ShipmentItemRequest r){ShipmentItem e=new ShipmentItem();apply(e,r);return repository.save(e);}
 public List<ShipmentItem> getAll(){return repository.findAllByIsActiveTrue();}
 public ShipmentItem getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("ShipmentItem not found: "+id));}
 public ShipmentItem update(Long id,ShipmentItemRequest r){ShipmentItem e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){ShipmentItem e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(ShipmentItem e,ShipmentItemRequest r){e.setQuantity(r.getQuantity());e.setShipment(shipmentService.getById(r.getShipmentId()));e.setProduct(productService.getById(r.getProductId()));}
}