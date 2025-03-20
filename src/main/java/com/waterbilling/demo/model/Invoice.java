package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceID")
    private Integer invoiceId;

    @Column(name = "IssueDate")
    private LocalDateTime issueDate;

    @Column(name = "DueDate")
    private LocalDateTime dueDate;

    @Column(name = "PaymentDate")
    private LocalDateTime paymentDate;

    @Column(name = "TotalAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "CreatedBy", foreignKey = @ForeignKey(name = "fk_invoice_employee"))
    private Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "PaidBy", foreignKey = @ForeignKey(name = "fk_invoice_user"))
    private User paidBy;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_invoice_facility"))
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "CollectorID", foreignKey = @ForeignKey(name = "fk_invoice_employee_collector"))
    private Employee collector;

    @Column(name = "CreationDate", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "PenaltyFee", nullable = false, precision = 10, scale = 2)
    private BigDecimal penaltyFee = BigDecimal.ZERO;

    public enum InvoiceStatus {
        paid, unpaid, cancelled
    }

    // Constructors
    public Invoice() {
    }

    public Invoice(LocalDateTime issueDate, LocalDateTime dueDate, BigDecimal totalAmount, InvoiceStatus status, Employee createdBy, User paidBy, Facility facility, Employee collector) {
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdBy = createdBy;
        this.paidBy = paidBy;
        this.facility = facility;
        this.collector = collector;
        this.creationDate = LocalDateTime.now();
    }

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public User getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Employee getCollector() {
		return collector;
	}

	public void setCollector(Employee collector) {
		this.collector = collector;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public BigDecimal getPenaltyFee() {
		return penaltyFee;
	}

	public void setPenaltyFee(BigDecimal penaltyFee) {
		this.penaltyFee = penaltyFee;
	}

}
