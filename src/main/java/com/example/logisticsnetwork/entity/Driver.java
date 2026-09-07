package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Driver extends BaseClass {
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,unique=true,length=80) private String licenseNumber;
 @Column(length=30) private String phoneNumber;
 @Column(nullable=false,length=40) private String status;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="carrier_id",nullable=false) private Carrier carrier;
 @JsonIgnore @OneToMany(mappedBy="driver") @Builder.Default private List<Route> routes=new ArrayList<>();
}