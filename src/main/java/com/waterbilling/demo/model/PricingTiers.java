package com.waterbilling.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.type.CustomType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "PricingTiers")
public class PricingTiers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TierID")
    Integer tierId;
    
    @ManyToOne													//fk_facility_customertype"
    @JoinColumn(name = "TypeID", foreignKey = @ForeignKey(name = "fk_pricingTier_facilityType"))
    FacilityType facilityType;

    @Column(name = "MinUsage", nullable = false, precision = 10, scale = 2)
    BigDecimal minUsage;

    @Column(name = "MaxUsage", precision = 10, scale = 2)
    BigDecimal maxUsage;

    @Column(name = "PricePerM3", nullable = false, precision = 10, scale = 2)
    BigDecimal pricePerM3;

}
