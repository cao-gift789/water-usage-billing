package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacilityID")
    Integer facilityId;

    @Column(name = "Address", length = 255)
    String address;

    @Column(name = "RegistrationDate", updatable = false)
    LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "OwnerId", foreignKey = @ForeignKey(name = "fk_facility_user"))
    @JsonBackReference(value = "user-facility") // Bên ngược lại
    User user;


    @ManyToOne
    @JoinColumn(name = "FacilityTypeID", foreignKey = @ForeignKey(name = "fk_facility_customertype"))
    FacilityType facilityType;
    

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<WaterMeter> waterMeters = new HashSet<>();
    
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<NotificationFacility> notificationFacilities =new HashSet<>();


    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Invoice> invoices = new HashSet<>();
    
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<JoinRequest> joinRequests = new HashSet<>();
    
    @Column(name = "IsActive")
    Boolean isActive = true;

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

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public Set<WaterMeter> getWaterMeters() {
		return waterMeters;
	}

	public void setWaterMeters(Set<WaterMeter> waterMeters) {
		this.waterMeters = waterMeters;
	}

	public Set<NotificationFacility> getNotificationFacilities() {
		return notificationFacilities;
	}

	public void setNotificationFacilities(Set<NotificationFacility> notificationFacilities) {
		this.notificationFacilities = notificationFacilities;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    
    
    
    
    
    
    
    
}
