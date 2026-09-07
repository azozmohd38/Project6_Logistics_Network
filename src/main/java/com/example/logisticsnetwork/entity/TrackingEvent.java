package com.example.logisticsnetwork.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrackingEvent extends BaseClass {
 @Column(nullable=false) private LocalDateTime eventTime;
 @Column(nullable=false,length=160) private String location;
 @Column(nullable=false,length=50) private String status;
 @Column(length=500) private String note;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="shipment_id",nullable=false) private Shipment shipment;
}