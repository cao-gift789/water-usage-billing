package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacilityID")
    private Integer facilityId;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "RegistrationDate", updatable = false)
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "OwnerId", foreignKey = @ForeignKey(name = "fk_facility_user"))
    private User owner;

    @ManyToOne
    @JoinColumn(name = "CustomerTypeID", foreignKey = @ForeignKey(name = "fk_facility_customertype"))
    private CustomerType customerType;

    @Column(name = "IsActive")
    private Boolean isActive = true;

    // Constructors
    public Facility() {
    }

    public Facility(String address, User owner, CustomerType customerType) {
        this.address = address;
        this.owner = owner;
        this.customerType = customerType;
        this.registrationDate = LocalDateTime.now();
        this.isActive = true;
    }

    // Getters and Setters
    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
