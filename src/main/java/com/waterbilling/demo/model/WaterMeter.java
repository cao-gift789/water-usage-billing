package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "WaterMeter")
public class WaterMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WaterMeterID")
    Integer waterMeterId;

    @Column(name = "SerialNumber", nullable = false, length = 50)
    String serialNumber;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_watermeter_facility"))
    @JsonIgnore 
    Facility facility;


    @Column(name = "InstallationDate")
    LocalDateTime installationDate = LocalDateTime.now();

    @Column(name = "IsActive")
    Boolean isActive = true;
    
    @OneToMany(mappedBy = "waterMeter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<WaterMeterReading> waterMeterReadings =new HashSet<>();

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

	public Set<WaterMeterReading> getWaterMeterReadings() {
		return waterMeterReadings;
	}

	public void setWaterMeterReadings(Set<WaterMeterReading> waterMeterReadings) {
		this.waterMeterReadings = waterMeterReadings;
	}


    
    
}
