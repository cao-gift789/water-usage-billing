package com.waterbilling.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FixedPricing")
public class FixedPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FixedPriceID")
    private Integer fixedPriceId;

    @ManyToOne
    @JoinColumn(name = "TypeID", foreignKey = @ForeignKey(name = "fk_fixedpricing_customertype"))
    private FacilityType fixedPricing_facilityType;

    @Column(name = "PricePerM3", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerM3;

    // Constructors
    public FixedPricing() {
    }

    public FixedPricing(FacilityType fixedPricing_facilityType, BigDecimal pricePerM3) {
        this.fixedPricing_facilityType = fixedPricing_facilityType;
        this.pricePerM3 = pricePerM3;
    }

	public Integer getFixedPriceId() {
		return fixedPriceId;
	}

	public void setFixedPriceId(Integer fixedPriceId) {
		this.fixedPriceId = fixedPriceId;
	}
	

	

	public FacilityType getFixedPricing_facilityType() {
		return fixedPricing_facilityType;
	}

	public void setFixedPricing_facilityType(FacilityType fixedPricing_facilityType) {
		this.fixedPricing_facilityType = fixedPricing_facilityType;
	}

	public BigDecimal getPricePerM3() {
		return pricePerM3;
	}

	public void setPricePerM3(BigDecimal pricePerM3) {
		this.pricePerM3 = pricePerM3;
	}

}
