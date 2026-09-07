package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Route extends BaseClass {
 @Column(nullable=false) private LocalDate routeDate;
 @Column(nullable=false,length=160) private String origin;
 @Column(nullable=false,length=160) private String destination;
 @Column(nullable=false,length=40) private String status;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="vehicle_id",nullable=false) private Vehicle vehicle;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="driver_id",nullable=false) private Driver driver;
 @JsonIgnore @OneToMany(mappedBy="route",cascade=CascadeType.ALL,orphanRemoval=true) @OrderBy("sequence ASC") @Builder.Default private List<DeliveryStop> deliveryStops=new ArrayList<>();
}