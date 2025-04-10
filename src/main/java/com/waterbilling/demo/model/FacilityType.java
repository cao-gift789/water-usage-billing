package com.waterbilling.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "FacilityType")
public class FacilityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    Integer typeId;

    @Column(name = "TypeName", unique = true, length = 100)
    String typeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CalculationMethod")
    CalculationMethod calculationMethod;
    
    @OneToMany(mappedBy = "facilityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Facility> facilities =new HashSet<>();
    
    @OneToMany(mappedBy = "facilityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<PricingTiers> pricingTiers =new HashSet<>();
    
    @OneToOne(mappedBy = "facilityType", cascade = CascadeType.ALL)
    FixedPricing fixedPricing;

    enum CalculationMethod {
        Fixed, Tiered
    }

}
