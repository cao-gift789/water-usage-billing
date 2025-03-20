package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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
    private Facility facility;

    @Column(name = "InstallationDate")
    private LocalDateTime installationDate;

    @Column(name = "IsActive")
    private Boolean isActive = true;

    // Constructors
    public WaterMeter() {
    }

    public WaterMeter(String serialNumber, Facility facility, LocalDateTime installationDate) {
        this.serialNumber = serialNumber;
        this.facility = facility;
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

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
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
