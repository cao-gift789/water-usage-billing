package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LocationManager")
public class LocationManager {

    
	@Id
    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_locationmanager_user"))
    private User locationManager_user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_locationmanager_facility"))
    private Facility locationManager_facility;

    @Column(name = "GrantedDate", updatable = false)
    private LocalDateTime grantedDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;

    // Constructors
    public LocationManager() {
    }

    public LocationManager(User user, Facility facility) {
        this.locationManager_user = user;
        this.locationManager_facility = facility;
        this.grantedDate = LocalDateTime.now();
        this.isActive = true;
    }

	

	

	public User getLocationManager_user() {
		return locationManager_user;
	}

	public void setLocationManager_user(User locationManager_user) {
		this.locationManager_user = locationManager_user;
	}

	public Facility getLocationManager_facility() {
		return locationManager_facility;
	}

	public void setLocationManager_facility(Facility locationManager_facility) {
		this.locationManager_facility = locationManager_facility;
	}

	public LocalDateTime getGrantedDate() {
		return grantedDate;
	}

	public void setGrantedDate(LocalDateTime grantedDate) {
		this.grantedDate = grantedDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
