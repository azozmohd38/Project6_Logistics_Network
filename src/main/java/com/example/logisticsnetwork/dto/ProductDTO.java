package com.example.logisticsnetwork.dto;
import com.example.logisticsnetwork.entity.Product;
import lombok.*;
import java.util.List;
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
 private Long id; private String name; private String sku; private Double weightKg; private String category;
 public static ProductDTO convertToDTO(Product e){return ProductDTO.builder().id(e.getId()).name(e.getName()).sku(e.getSku()).weightKg(e.getWeightKg()).category(e.getCategory()).build();}
 public static List<ProductDTO> convertToDTO(List<Product> list){return list.stream().map(ProductDTO::convertToDTO).toList();}
}