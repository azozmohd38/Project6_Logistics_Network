package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.InventoryItemRequest;
import com.example.logisticsnetwork.entity.InventoryItem;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class InventoryItemService {
 private final InventoryItemRepository repository; private final WarehouseService warehouseService; private final ProductService productService;
 public InventoryItemService(InventoryItemRepository repository,WarehouseService warehouseService,ProductService productService){this.repository=repository;this.warehouseService=warehouseService;this.productService=productService;}
 public InventoryItem create(InventoryItemRequest r){InventoryItem e=new InventoryItem();apply(e,r);return repository.save(e);}
 public List<InventoryItem> getAll(){return repository.findAllByIsActiveTrue();}
 public InventoryItem getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("InventoryItem not found: "+id));}
 public InventoryItem update(Long id,InventoryItemRequest r){InventoryItem e=getById(id);apply(e,r);return repository.save(e);}
 public void delete(Long id){InventoryItem e=getById(id);e.setIsActive(false);repository.save(e);}
 public InventoryItem save(InventoryItem e){return repository.save(e);}
 private void apply(InventoryItem e,InventoryItemRequest r){e.setQuantity(r.getQuantity());e.setShelfLocation(r.getShelfLocation());e.setWarehouse(warehouseService.getById(r.getWarehouseId()));e.setProduct(productService.getById(r.getProductId()));}
}