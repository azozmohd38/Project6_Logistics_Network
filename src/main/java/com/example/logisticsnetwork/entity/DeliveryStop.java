package com.example.logisticsnetwork.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"route_id","sequence"}))
public class DeliveryStop extends BaseClass {
 @Column(nullable=false) private Integer sequence;
 @Column(nullable=false,length=200) private String address;
 @Column(nullable=false,length=40) private String status;
 private LocalDateTime eta;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="route_id",nullable=false) private Route route;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="shipment_id",nullable=false) private Shipment shipment;
}