package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceID")
    private Integer invoiceId;

//    @Column(name = "IssueDate")
//    private LocalDateTime issueDate;
//
//    @Column(name = "DueDate")
//    private LocalDateTime dueDate;

    @Column(name = "PaymentDate")
    private LocalDateTime paymentDate;

    @Column(name = "TotalAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private InvoiceStatus status;

    @ManyToOne
    @JoinColumn(name = "CreatedBy", foreignKey = @ForeignKey(name = "fk_invoice_employee"))
    private Employee invoice_employee;

    @ManyToOne
    @JoinColumn(name = "PaidBy", foreignKey = @ForeignKey(name = "fk_invoice_user"))
    private User invoice_user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_invoice_facility"))
    private Facility invoice_facility;

    @OneToMany(mappedBy = "transaction_invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> invoice_transaction =new ArrayList<>();
    
     					 //invoiceWaterMeterReading_invoice
    @OneToMany(mappedBy = "invoiceWaterMeterReading_invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvoiceWaterMeterReading> invoice_invoiceWaterMeterReading=new ArrayList<>();
    
    

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

    public Invoice(LocalDateTime issueDate, LocalDateTime dueDate, BigDecimal totalAmount, InvoiceStatus status, Employee createdBy, User paidBy, Facility facility) {
//        this.issueDate = issueDate;
//        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.invoice_employee = createdBy;
        this.invoice_user = paidBy;
        this.invoice_facility = facility;
        
        this.creationDate = LocalDateTime.now();
    }

    
	public List<Transaction> getInvoice_transaction() {
		return invoice_transaction;
	}

	public void setInvoice_transaction(List<Transaction> invoice_transaction) {
		this.invoice_transaction = invoice_transaction;
	}

	public List<InvoiceWaterMeterReading> getInvoice_invoiceWaterMeterReading() {
		return invoice_invoiceWaterMeterReading;
	}

	public void setInvoice_invoiceWaterMeterReading(List<InvoiceWaterMeterReading> invoice_invoiceWaterMeterReading) {
		this.invoice_invoiceWaterMeterReading = invoice_invoiceWaterMeterReading;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

//	public LocalDateTime getIssueDate() {
//		return issueDate;
//	}
//
//	public void setIssueDate(LocalDateTime issueDate) {
//		this.issueDate = issueDate;
//	}
//
//	public LocalDateTime getDueDate() {
//		return dueDate;
//	}
//
//	public void setDueDate(LocalDateTime dueDate) {
//		this.dueDate = dueDate;
//	}

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

	
	
	
	
	
	
	

	public Employee getInvoice_employee() {
		return invoice_employee;
	}

	public void setInvoice_employee(Employee invoice_employee) {
		this.invoice_employee = invoice_employee;
	}



	
	public User getInvoice_user() {
		return invoice_user;
	}

	public void setInvoice_user(User invoice_user) {
		this.invoice_user = invoice_user;
	}

	public Facility getInvoice_facility() {
		return invoice_facility;
	}

	public void setInvoice_facility(Facility invoice_facility) {
		this.invoice_facility = invoice_facility;
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
