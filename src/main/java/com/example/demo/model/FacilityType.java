package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "CustomerType")
public class FacilityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private Integer typeId;

    @Column(name = "TypeName", unique = true, length = 100)
    private String typeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CalculationMethod")
    private CalculationMethod calculationMethod;
    
    @OneToMany(mappedBy = "facility_facilityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Facility> facilityType_facility=new ArrayList<>();
    
    @OneToMany(mappedBy = "pricingTier_facilityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PricingTiers>facilityType_pricingTiers=new ArrayList<>();
    
    @OneToMany(mappedBy = "fixedPricing_facilityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FixedPricing> facilityType_fixedPricing=new ArrayList<>();

    public enum CalculationMethod {
        Fixed, Tiered
    }

    // Constructors
    public FacilityType() {
    }

    public FacilityType(String typeName, CalculationMethod calculationMethod) {
        this.typeName = typeName;
        this.calculationMethod = calculationMethod;
    }

    // Getters and Setters
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CalculationMethod getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(CalculationMethod calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

	public List<Facility> getFacilityType_facility() {
		return facilityType_facility;
	}

	public void setFacilityType_facility(List<Facility> facilityType_facility) {
		this.facilityType_facility = facilityType_facility;
	}

	public List<PricingTiers> getFacilityType_pricingTiers() {
		return facilityType_pricingTiers;
	}

	public void setFacilityType_pricingTiers(List<PricingTiers> facilityType_pricingTiers) {
		this.facilityType_pricingTiers = facilityType_pricingTiers;
	}

	public List<FixedPricing> getFacilityType_fixedPricing() {
		return facilityType_fixedPricing;
	}

	public void setFacilityType_fixedPricing(List<FixedPricing> facilityType_fixedPricing) {
		this.facilityType_fixedPricing = facilityType_fixedPricing;
	}
    
}
