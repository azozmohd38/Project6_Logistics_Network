package com.example.logisticsnetwork.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Staff extends BaseClass {
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,length=80) private String role;
 @Column(length=30) private String phoneNumber;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="warehouse_id",nullable=false) private Warehouse warehouse;
}