package com.waterbilling.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Integer transactionID;
    private Integer invoiceID;
    private String paymentMethodName;
    private BigDecimal amount;
    private String status;
    private LocalDateTime transactionDate;
    private String referenceCode;
    private String message;
}
