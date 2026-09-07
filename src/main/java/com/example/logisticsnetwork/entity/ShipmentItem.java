package com.example.logisticsnetwork.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ShipmentItem extends BaseClass {
 @Column(nullable=false) private Integer quantity;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="shipment_id",nullable=false) private Shipment shipment;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="product_id",nullable=false) private Product product;
}