package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "FixedPricing")
public class FixedPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FixedPriceID")
    Integer fixedPriceId;

    @OneToOne
    @JoinColumn(name = "TypeID", foreignKey = @ForeignKey(name = "fk_fixedpricing_customertype"))
    FacilityType facilityType;

    @Column(name = "PricePerM3", nullable = false, precision = 10, scale = 2)
    BigDecimal pricePerM3;



}
