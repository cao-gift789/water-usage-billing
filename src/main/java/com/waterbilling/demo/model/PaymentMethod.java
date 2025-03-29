package com.waterbilling.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "PaymentMethod")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentMethodID")
    private Integer paymentMethodId;

    @Column(name = "MethodName", length = 50)
    private String methodName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "transaction_paymentMethod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> paymentMethod_transaction=new ArrayList<>();

    // Constructors
    public PaymentMethod() {
    }

    public PaymentMethod(String methodName, String description) {
        this.methodName = methodName;
        this.description = description;
    }

    
	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}

	
	public List<Transaction> getPaymentMethod_transaction() {
		return paymentMethod_transaction;
	}

	public void setPaymentMethod_transaction(List<Transaction> paymentMethod_transaction) {
		this.paymentMethod_transaction = paymentMethod_transaction;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
