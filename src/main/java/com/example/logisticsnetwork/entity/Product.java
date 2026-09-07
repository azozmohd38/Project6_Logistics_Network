package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product extends BaseClass {
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,unique=true,length=80) private String sku;
 @Column(nullable=false) private Double weightKg;
 @Column(length=80) private String category;
 @JsonIgnore @OneToMany(mappedBy="product") @Builder.Default private List<InventoryItem> inventoryItems=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="product") @Builder.Default private List<ShipmentItem> shipmentItems=new ArrayList<>();
}