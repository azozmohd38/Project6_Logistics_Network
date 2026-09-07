package com.example.logisticsnetwork.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invoice extends BaseClass {
 @Column(nullable=false,precision=12,scale=2) private BigDecimal amount;
 @Column(nullable=false,length=40) private String status;
 @Column(nullable=false) private LocalDate issuedDate;
 @OneToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="shipment_id",nullable=false,unique=true) private Shipment shipment;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
}