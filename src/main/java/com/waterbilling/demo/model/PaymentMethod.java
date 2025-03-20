package com.waterbilling.demo.model;

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
