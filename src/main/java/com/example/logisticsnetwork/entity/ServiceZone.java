package com.example.logisticsnetwork.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceZone extends BaseClass {
    @Column(nullable=false,length=100)
    private String name;

    @Column(nullable=false,length=120)
    private String region;

    @Column(nullable=false,precision=10,scale=2)
    private BigDecimal baseRate;

    @JsonIgnore
    @OneToMany(mappedBy="serviceZone")
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();
}