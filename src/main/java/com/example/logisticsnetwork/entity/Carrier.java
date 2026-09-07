package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Carrier extends BaseClass {
 @Column(nullable=false,length=120) private String name;
 @Column(length=150) private String contactEmail;
 @Column(length=30) private String phoneNumber;
 @Column(length=100) private String country;
 @JsonIgnore @OneToMany(mappedBy="carrier") @Builder.Default private List<Shipment> shipments=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="carrier") @Builder.Default private List<Vehicle> vehicles=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="carrier") @Builder.Default private List<Driver> drivers=new ArrayList<>();
}