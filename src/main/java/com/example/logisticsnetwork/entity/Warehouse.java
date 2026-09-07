package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Warehouse extends BaseClass {
 @Column(nullable=false,length=100) private String name;
 @Column(nullable=false,length=200) private String location;
 @Column(nullable=false) private Integer capacity;
 @JsonIgnore @OneToMany(mappedBy="warehouse") @Builder.Default private List<InventoryItem> inventoryItems=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="warehouse") @Builder.Default private List<Shipment> shipments=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="warehouse") @Builder.Default private List<Staff> staff=new ArrayList<>();
}