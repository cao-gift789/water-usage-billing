package com.waterbilling.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceDetailResponse {

    Integer invoiceId;
    Integer facilityId;
    LocalDateTime creationDate;
    LocalDateTime paymentDate;
    String status;
    String customerName;
    String address;
    Map<String, BigDecimal> waterMeterReadingResponses;
    BigDecimal totalConsumption;
    BigDecimal totalPrice;
    BigDecimal penaltyFee;
}
