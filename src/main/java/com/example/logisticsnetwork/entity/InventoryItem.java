package com.example.logisticsnetwork.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"warehouse_id","product_id"}))
public class InventoryItem extends BaseClass {
 @Column(nullable=false) private Integer quantity;
 @Column(length=100) private String shelfLocation;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="warehouse_id",nullable=false) private Warehouse warehouse;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="product_id",nullable=false) private Product product;
}