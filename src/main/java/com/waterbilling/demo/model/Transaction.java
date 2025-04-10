package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "InvoiceID", foreignKey = @ForeignKey(name = "fk_transaction_invoice"))
    Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "PaymentMethodID", foreignKey = @ForeignKey(name = "fk_transaction_paymentmethod"))
    PaymentMethod paymentMethod;

    @Column(name = "Amount", nullable = false, precision = 10, scale = 2)
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    TransactionStatus status;

    @Column(name = "TransactionDate", updatable = false)
    LocalDateTime transactionDate;

    @Column(name = "ReferenceCode", length = 100)
    String referenceCode;

    public enum TransactionStatus {
        completed, failed, pending
    }


}
