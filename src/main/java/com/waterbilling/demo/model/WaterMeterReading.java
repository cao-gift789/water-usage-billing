package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Formula;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "WaterMeterReading")
public class WaterMeterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReadingID")
    Integer readingId;

    @Column(name = "DateRecorded", nullable = false)
    LocalDateTime dateRecorded;

    @Column(name = "PreviousReading", nullable = false, precision = 10, scale = 2)
    BigDecimal previousReading;

    @Column(name = "CurrentReading", nullable = false, precision = 10, scale = 2)
    BigDecimal currentReading;

    @Formula("(CurrentReading - PreviousReading)")
    @Column(name = "WaterUsage", nullable = false, precision = 10, scale = 2)
	BigDecimal waterUsage;

    @ManyToOne
    @JoinColumn(name = "RecordedBy", foreignKey = @ForeignKey(name = "fk_watermeterreading_employee"))
    Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "InvoiceID", foreignKey = @ForeignKey(name = "fk_waterMeterReading_invoice"))
	Invoice invoice;

	@ManyToOne
	@JoinColumn(name = "WaterMeterID", foreignKey = @ForeignKey(name = "fk_watermeterreading_watermeter"))
	WaterMeter waterMeter;

	public Integer getReadingId() {
		return readingId;
	}

	public void setReadingId(Integer readingId) {
		this.readingId = readingId;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public WaterMeter getWaterMeter() {
		return waterMeter;
	}

	public void setWaterMeter(WaterMeter waterMeter) {
		this.waterMeter = waterMeter;
	}

	
	
	
}
