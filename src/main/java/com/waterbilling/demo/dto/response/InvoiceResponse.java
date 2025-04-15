package com.waterbilling.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceResponse {

    Integer invoiceId;
    BigDecimal totalAmount;
    String status;
    LocalDateTime creationDate;
    BigDecimal penaltyFee;

}
