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
    @JoinColumn(name = "PaidBy", foreignKey = @ForeignKey(name = "fk_invoice_user"))
    User user;

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


}
