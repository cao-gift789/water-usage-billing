package com.example.demo.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "InvoiceID", foreignKey = @ForeignKey(name = "fk_transaction_invoice"))
    private Invoice transaction_invoice;
    

    @ManyToOne
    @JoinColumn(name = "PaymentMethodID", foreignKey = @ForeignKey(name = "fk_transaction_paymentmethod"))
    private PaymentMethod transaction_paymentMethod;

    @Column(name = "Amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private TransactionStatus status;

    @Column(name = "TransactionDate", updatable = false)
    private LocalDateTime transactionDate;

    @Column(name = "ReferenceCode", length = 100)
    private String referenceCode;

    public enum TransactionStatus {
        completed, failed, pending
    }

    // Constructors
    public Transaction() {
    }

    public Transaction(Invoice invoice, PaymentMethod paymentMethod, BigDecimal amount, TransactionStatus status, String referenceCode) {
        this.transaction_invoice = invoice;
        this.transaction_paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.transactionDate = LocalDateTime.now();
        this.referenceCode = referenceCode;
    }

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	

	public Invoice getTransaction_invoice() {
		return transaction_invoice;
	}

	public void setTransaction_invoice(Invoice transaction_invoice) {
		this.transaction_invoice = transaction_invoice;
	}

	public PaymentMethod getTransaction_paymentMethod() {
		return transaction_paymentMethod;
	}

	public void setTransaction_paymentMethod(PaymentMethod transaction_paymentMethod) {
		this.transaction_paymentMethod = transaction_paymentMethod;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

}
