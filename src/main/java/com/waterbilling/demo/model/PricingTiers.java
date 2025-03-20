package com.waterbilling.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PricingTiers")
public class PricingTiers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TierID")
    private Integer tierId;

    @ManyToOne
    @JoinColumn(name = "TypeID", foreignKey = @ForeignKey(name = "fk_pricingtiers_customertype"))
    private CustomerType customerType;

    @Column(name = "MinUsage", nullable = false, precision = 10, scale = 2)
    private BigDecimal minUsage;

    @Column(name = "MaxUsage", precision = 10, scale = 2)
    private BigDecimal maxUsage;

    @Column(name = "PricePerM3", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerM3;

    // Constructors
    public PricingTiers() {
    }

    public PricingTiers(CustomerType customerType, BigDecimal minUsage, BigDecimal maxUsage, BigDecimal pricePerM3) {
        this.customerType = customerType;
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

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
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
