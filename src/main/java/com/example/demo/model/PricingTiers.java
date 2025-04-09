package com.example.demo.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

import org.hibernate.type.CustomType;

@Entity
@Table(name = "PricingTiers")
public class PricingTiers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TierID")
    private Integer tierId;
    
    @ManyToOne													//fk_facility_customertype"
    @JoinColumn(name = "TypeID", foreignKey = @ForeignKey(name = "fk_pricingTier_facilityType"))
    private FacilityType pricingTier_facilityType;

    @Column(name = "MinUsage", nullable = false, precision = 10, scale = 2)
    private BigDecimal minUsage;

    @Column(name = "MaxUsage", precision = 10, scale = 2)
    private BigDecimal maxUsage;

    @Column(name = "PricePerM3", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerM3;

    // Constructors
    public PricingTiers() {
    }

    public PricingTiers(FacilityType pricingTier_facilityType, BigDecimal minUsage, BigDecimal maxUsage, BigDecimal pricePerM3) {
        this.pricingTier_facilityType = pricingTier_facilityType;
        this.minUsage = minUsage;
        this.maxUsage = maxUsage;
        this.pricePerM3 = pricePerM3;
    }

	public Integer getTierId() {
		return tierId;
	}
	public void setTierId(Integer tierId) {
		this.tierId = tierId;
	}
	

	

	
	public FacilityType getPricingTier_facilityType() {
		return pricingTier_facilityType;
	}

	public void setPricingTier_facilityType(FacilityType pricingTier_facilityType) {
		this.pricingTier_facilityType = pricingTier_facilityType;
	}

	public BigDecimal getMinUsage() {
		return minUsage;
	}

	public void setMinUsage(BigDecimal minUsage) {
		this.minUsage = minUsage;
	}

	public BigDecimal getMaxUsage() {
		return maxUsage;
	}

	public void setMaxUsage(BigDecimal maxUsage) {
		this.maxUsage = maxUsage;
	}

	public BigDecimal getPricePerM3() {
		return pricePerM3;
	}

	public void setPricePerM3(BigDecimal pricePerM3) {
		this.pricePerM3 = pricePerM3;
	}

}
