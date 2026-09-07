package com.example.logisticsnetwork.service;
import com.example.logisticsnetwork.dto.request.ProductRequest;
import com.example.logisticsnetwork.entity.Product;
import com.example.logisticsnetwork.exception.ResourceNotFoundException;
import com.example.logisticsnetwork.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
 private final ProductRepository repository;
 public ProductService(ProductRepository repository){this.repository=repository;}
 public Product create(ProductRequest r){Product e=new Product(); apply(e,r); return repository.save(e);}
 public List<Product> getAll(){return repository.findAllByIsActiveTrue();}
 public Product getById(Long id){return repository.findByIdAndIsActiveTrue(id).orElseThrow(()->new ResourceNotFoundException("Product not found: "+id));}
 public Product update(Long id,ProductRequest r){Product e=getById(id); apply(e,r); return repository.save(e);}
 public void delete(Long id){Product e=getById(id);e.setIsActive(false);repository.save(e);}
 private void apply(Product e,ProductRequest r){e.setName(r.getName());e.setSku(r.getSku());e.setWeightKg(r.getWeightKg());e.setCategory(r.getCategory());}
}