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
    private CustomerType customerType;

    @Column(name = "PricePerM3", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerM3;

    // Constructors
    public FixedPricing() {
    }

    public FixedPricing(CustomerType customerType, BigDecimal pricePerM3) {
        this.customerType = customerType;
        this.pricePerM3 = pricePerM3;
    }

	public Integer getFixedPriceId() {
		return fixedPriceId;
	}

	public void setFixedPriceId(Integer fixedPriceId) {
		this.fixedPriceId = fixedPriceId;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public BigDecimal getPricePerM3() {
		return pricePerM3;
	}

	public void setPricePerM3(BigDecimal pricePerM3) {
		this.pricePerM3 = pricePerM3;
	}

}
