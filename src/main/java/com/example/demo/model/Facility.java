package com.example.demo.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private User facility_user;

    @ManyToOne
    @JoinColumn(name = "CustomerTypeID", foreignKey = @ForeignKey(name = "fk_facility_customertype"))
    private FacilityType facility_facilityType;
    
    
    
    
    @OneToMany(mappedBy = "waterMeter_facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WaterMeter> facility_waterMeter=new ArrayList<>();
    
    @OneToMany(mappedBy = "notificationFacility_facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NotificationFacility> facility_notificationFacility_=new ArrayList<>();
    					 //joinRequest_facility
    @OneToMany(mappedBy = "joinRequest_facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JoinRequest> facility_joinRequest=new ArrayList<>();
    
    					 //locationManager_facility
    @OneToMany(mappedBy = "locationManager_facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LocationManager> facility_locationManager=new ArrayList<>();
    
    @OneToMany(mappedBy = "invoice_facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> facility_invoice=new ArrayList<>();
   

    @Column(name = "IsActive")
    private Boolean isActive = true;

    // Constructors
    public Facility() {
    }

    public Facility(String address, User owner, FacilityType facility_facilityType) {
        this.address = address;
        this.facility_user = owner;
        this.facility_facilityType = facility_facilityType;
        this.registrationDate = LocalDateTime.now();
        this.isActive = true;
    }

    // Getters and Setters
    public Integer getFacilityId() {
        return facilityId;
    }
    

    public User getFacility_user() {
		return facility_user;
	}

	public void setFacility_user(User facility_user) {
		this.facility_user = facility_user;
	}

	public List<NotificationFacility> getFacility_notificationFacility_() {
		return facility_notificationFacility_;
	}

	public void setFacility_notificationFacility_(List<NotificationFacility> facility_notificationFacility_) {
		this.facility_notificationFacility_ = facility_notificationFacility_;
	}

	public List<LocationManager> getFacility_locationManager() {
		return facility_locationManager;
	}

	public void setFacility_locationManage(List<LocationManager> facility_locationManager) {
		this.facility_locationManager = facility_locationManager;
	}

	public List<Invoice> getFacility_invoice() {
		return facility_invoice;
	}

	public void setFacility_invoice(List<Invoice> facility_invoice) {
		this.facility_invoice = facility_invoice;
	}

	public List<WaterMeter> getFacility_waterMeter() {
		return facility_waterMeter;
	}

	public void setFacility_waterMeter(List<WaterMeter> facility_waterMeter) {
		this.facility_waterMeter = facility_waterMeter;
	}

	public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getAddress() {
        return address;
    }

    
    
    
    public FacilityType getFacility_facilityType() {
		return facility_facilityType;
	}

	public void setFacility_facilityType(FacilityType facility_facilityType) {
		this.facility_facilityType = facility_facilityType;
	}

	

	

	public List<JoinRequest> getFacility_joinRequest() {
		return facility_joinRequest;
	}

	public void setFacility_joinRequest(List<JoinRequest> facility_joinRequest) {
		this.facility_joinRequest = facility_joinRequest;
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

   

    
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
