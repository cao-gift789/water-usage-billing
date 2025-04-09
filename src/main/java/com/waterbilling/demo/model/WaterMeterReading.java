package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "WaterMeterReading")
public class WaterMeterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReadingID")
    private Integer readingId;

    @ManyToOne
    @JoinColumn(name = "WaterMeterID", foreignKey = @ForeignKey(name = "fk_watermeterreading_watermeter"))
    private WaterMeter waterMeterReading_waterMeter;

    @Column(name = "DateRecorded", nullable = false)
    private LocalDateTime dateRecorded;

    @Column(name = "PreviousReading", nullable = false, precision = 10, scale = 2)
    private BigDecimal previousReading;

    @Column(name = "CurrentReading", nullable = false, precision = 10, scale = 2)
    private BigDecimal currentReading;

    @Formula("(CurrentReading - PreviousReading)")
    private BigDecimal waterUsage;

    @ManyToOne
    @JoinColumn(name = "RecordedBy", foreignKey = @ForeignKey(name = "fk_watermeterreading_employee"))
    private Employee WaterMeterReading_employee;
    
    @ManyToOne
    @JoinColumn(name = "InvoiceID", foreignKey = @ForeignKey(name = "fk_waterMeterReading_invoice"))
    private Invoice waterMeterReading_invoice;
    


    // Constructors
    public WaterMeterReading() {
    }

    public WaterMeterReading(WaterMeter waterMeter, BigDecimal previousReading, BigDecimal currentReading, BigDecimal waterUsage, Employee recordedBy) {
        this.waterMeterReading_waterMeter = waterMeter;
        this.dateRecorded = LocalDateTime.now();
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.waterUsage = waterUsage;
        this.WaterMeterReading_employee = recordedBy;
    }

	public Integer getReadingId() {
		return readingId;
	}

	public void setReadingId(Integer readingId) {
		this.readingId = readingId;
	}

	

	public WaterMeter getWaterMeterReading_waterMeter() {
		return waterMeterReading_waterMeter;
	}

	public Invoice getWaterMeterReading_invoice() {
		return waterMeterReading_invoice;
	}

	public void setWaterMeterReading_invoice(Invoice waterMeterReading_invoice) {
		this.waterMeterReading_invoice = waterMeterReading_invoice;
	}

	public void setWaterMeterReading_waterMeter(WaterMeter waterMeterReading_waterMeter) {
		this.waterMeterReading_waterMeter = waterMeterReading_waterMeter;
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

	public Employee getWaterMeterReading_employee() {
		return WaterMeterReading_employee;
	}

	public void setWaterMeterReading_employee(Employee waterMeterReading_employee) {
		WaterMeterReading_employee = waterMeterReading_employee;
	}

	

}
