package com.example.logisticsnetwork.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Address extends BaseClass {
 @Column(nullable=false,length=180) private String street;
 @Column(nullable=false,length=100) private String city;
 @Column(length=20) private String postalCode;
 @Column(nullable=false,length=100) private String country;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
}