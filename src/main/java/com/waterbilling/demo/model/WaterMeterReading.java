package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "WaterMeterReading")
public class WaterMeterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReadingID")
    private Integer readingId;

    @ManyToOne
    @JoinColumn(name = "WaterMeterID", foreignKey = @ForeignKey(name = "fk_watermeterreading_watermeter"))
    private WaterMeter waterMeter;

    @Column(name = "DateRecorded", nullable = false)
    private LocalDateTime dateRecorded;

    @Column(name = "PreviousReading", nullable = false, precision = 10, scale = 2)
    private BigDecimal previousReading;

    @Column(name = "CurrentReading", nullable = false, precision = 10, scale = 2)
    private BigDecimal currentReading;

    @Column(name = "WaterUsage", nullable = false, precision = 10, scale = 2)
    private BigDecimal waterUsage;

    @ManyToOne
    @JoinColumn(name = "RecordedBy", foreignKey = @ForeignKey(name = "fk_watermeterreading_employee"))
    private Employee recordedBy;

    // Constructors
    public WaterMeterReading() {
    }

    public WaterMeterReading(WaterMeter waterMeter, BigDecimal previousReading, BigDecimal currentReading, BigDecimal waterUsage, Employee recordedBy) {
        this.waterMeter = waterMeter;
        this.dateRecorded = LocalDateTime.now();
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.waterUsage = waterUsage;
        this.recordedBy = recordedBy;
    }

	public Integer getReadingId() {
		return readingId;
	}

	public void setReadingId(Integer readingId) {
		this.readingId = readingId;
	}

	public WaterMeter getWaterMeter() {
		return waterMeter;
	}

	public void setWaterMeter(WaterMeter waterMeter) {
		this.waterMeter = waterMeter;
	}

	public LocalDateTime getDateRecorded() {
		return dateRecorded;
	}

	public void setDateRecorded(LocalDateTime dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	public BigDecimal getPreviousReading() {
		return previousReading;
	}

	public void setPreviousReading(BigDecimal previousReading) {
		this.previousReading = previousReading;
	}

	public BigDecimal getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(BigDecimal currentReading) {
		this.currentReading = currentReading;
	}

	public BigDecimal getWaterUsage() {
		return waterUsage;
	}

	public void setWaterUsage(BigDecimal waterUsage) {
		this.waterUsage = waterUsage;
	}

	public Employee getRecordedBy() {
		return recordedBy;
	}

	public void setRecordedBy(Employee recordedBy) {
		this.recordedBy = recordedBy;
	}

}
