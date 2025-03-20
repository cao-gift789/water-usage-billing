package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LocationManager")
public class LocationManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ManagerID")
    private Integer managerId;

    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_locationmanager_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_locationmanager_facility"))
    private Facility facility;

    @Column(name = "GrantedDate", updatable = false)
    private LocalDateTime grantedDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;

    // Constructors
    public LocationManager() {
    }

    public LocationManager(User user, Facility facility) {
        this.user = user;
        this.facility = facility;
        this.grantedDate = LocalDateTime.now();
        this.isActive = true;
    }

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
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
