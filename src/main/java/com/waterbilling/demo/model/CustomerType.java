package com.waterbilling.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CustomerType")
public class CustomerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private Integer typeId;

    @Column(name = "TypeName", unique = true, length = 100)
    private String typeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CalculationMethod")
    private CalculationMethod calculationMethod;

    public enum CalculationMethod {
        Fixed, Tiered
    }

    // Constructors
    public CustomerType() {
    }

    public CustomerType(String typeName, CalculationMethod calculationMethod) {
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
}
