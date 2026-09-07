package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer extends BaseClass {
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,unique=true,length=150) private String email;
 @Column(length=30) private String phoneNumber;
 @Column(length=50) private String type;
 @JsonIgnore @OneToMany(mappedBy="customer") @Builder.Default private List<Shipment> shipments=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="customer",cascade=CascadeType.ALL,orphanRemoval=true) @Builder.Default private List<Address> addresses=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="customer") @Builder.Default private List<Invoice> invoices=new ArrayList<>();
}