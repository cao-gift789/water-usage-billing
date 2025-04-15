package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
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
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceID")
    Integer invoiceId;

    @Column(name = "PaymentDate")
    LocalDateTime paymentDate;

    @Column(name = "TotalAmount", nullable = false, precision = 10, scale = 2)
    BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "CreatedBy", foreignKey = @ForeignKey(name = "fk_invoice_employee"))
    Employee employee;


    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_invoice_facility"))
    Facility facility;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Transaction> transactions =new HashSet<>();

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<WaterMeterReading> waterMeterReadings =new HashSet<>();

    @Column(name = "CreationDate", updatable = false)
    LocalDateTime creationDate;

    @Column(name = "PenaltyFee", nullable = false, precision = 10, scale = 2)
    BigDecimal penaltyFee = BigDecimal.ZERO;

    public enum InvoiceStatus {
        paid, unpaid, cancelled, overdue_penalty, suspended
    }

//	public Integer getInvoiceId() {
//		return invoiceId;
//	}
//
//	public void setInvoiceId(Integer invoiceId) {
//		this.invoiceId = invoiceId;
//	}
//
//	public LocalDateTime getPaymentDate() {
//		return paymentDate;
//	}
//
//	public void setPaymentDate(LocalDateTime paymentDate) {
//		this.paymentDate = paymentDate;
//	}
//
//	public BigDecimal getTotalAmount() {
//		return totalAmount;
//	}
//
//	public void setTotalAmount(BigDecimal totalAmount) {
//		this.totalAmount = totalAmount;
//	}
//
//	public InvoiceStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(InvoiceStatus status) {
//		this.status = status;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Facility getFacility() {
//		return facility;
//	}
//
//	public void setFacility(Facility facility) {
//		this.facility = facility;
//	}
//
//	public Set<Transaction> getTransactions() {
//		return transactions;
//	}
//
//	public void setTransactions(Set<Transaction> transactions) {
//		this.transactions = transactions;
//	}
//
//	public Set<WaterMeterReading> getWaterMeterReadings() {
//		return waterMeterReadings;
//	}
//
//	public void setWaterMeterReadings(Set<WaterMeterReading> waterMeterReadings) {
//		this.waterMeterReadings = waterMeterReadings;
//	}
//
//	public LocalDateTime getCreationDate() {
//		return creationDate;
//	}
//
//	public void setCreationDate(LocalDateTime creationDate) {
//		this.creationDate = creationDate;
//	}
//
//	public BigDecimal getPenaltyFee() {
//		return penaltyFee;
//	}
//
//	public void setPenaltyFee(BigDecimal penaltyFee) {
//		this.penaltyFee = penaltyFee;
//	}

    

}
