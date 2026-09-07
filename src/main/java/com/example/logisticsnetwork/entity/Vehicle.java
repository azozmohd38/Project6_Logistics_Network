package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicle extends BaseClass {
 @Column(nullable=false,unique=true,length=40) private String plateNumber;
 @Column(nullable=false,length=60) private String type;
 @Column(nullable=false) private Double capacityKg;
 @Column(nullable=false,length=40) private String status;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="carrier_id",nullable=false) private Carrier carrier;
 @JsonIgnore @OneToMany(mappedBy="vehicle") @Builder.Default private List<Route> routes=new ArrayList<>();
}