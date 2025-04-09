package com.example.demo.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WaterMeter")
public class WaterMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WaterMeterID")
    private Integer waterMeterId;

    @Column(name = "SerialNumber", nullable = false, length = 50)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_watermeter_facility"))
    private Facility waterMeter_facility;

    @Column(name = "InstallationDate")
    private LocalDateTime installationDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;
    
    @OneToMany(mappedBy = "waterMeterReading_waterMeter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WaterMeterReading> waterMeter_waterMeterReading=new ArrayList<>();

    // Constructors
    public WaterMeter() {
    }

    public WaterMeter(String serialNumber, Facility waterMeter_facility, LocalDateTime installationDate) {
        this.serialNumber = serialNumber;
        this.waterMeter_facility = waterMeter_facility;
        this.installationDate = installationDate;
        this.isActive = true;
    }

	public Integer getWaterMeterId() {
		return waterMeterId;
	}

	public void setWaterMeterId(Integer waterMeterId) {
		this.waterMeterId = waterMeterId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	

	public Facility getWaterMeter_facility() {
		return waterMeter_facility;
	}

	public void setWaterMeter_facility(Facility waterMeter_facility) {
		this.waterMeter_facility = waterMeter_facility;
	}

	public LocalDateTime getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(LocalDateTime installationDate) {
		this.installationDate = installationDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
