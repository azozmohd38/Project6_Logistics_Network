package com.example.logisticsnetwork.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Shipment extends BaseClass {
 @Column(nullable=false) private LocalDate shipmentDate;
 @Column(nullable=false,length=50) private String status;
 @Column(nullable=false) private Double totalWeight;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="warehouse_id",nullable=false) private Warehouse warehouse;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="carrier_id") private Carrier carrier;
 @JsonIgnore @OneToMany(mappedBy="shipment",cascade=CascadeType.ALL,orphanRemoval=true) @Builder.Default private List<ShipmentItem> shipmentItems=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="shipment",cascade=CascadeType.ALL,orphanRemoval=true) @Builder.Default private List<TrackingEvent> trackingEvents=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="shipment") @Builder.Default private List<DeliveryStop> deliveryStops=new ArrayList<>();
}