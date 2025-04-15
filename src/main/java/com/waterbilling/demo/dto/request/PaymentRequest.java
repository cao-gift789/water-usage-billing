package com.waterbilling.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;


@Getter
public class PaymentRequest {

    @NotNull(message = "Id không được null")
    @Positive(message = "Id phải là số dương")
    private Integer invoiceID;

    @NotNull(message = "Id không được null")
    @Positive(message = "Id phải là số dương")
    private Integer paymentMethodID;

    @Positive(message = "giá không được là số âm")
    private BigDecimal amount;

    private String additionalInfo;
}
