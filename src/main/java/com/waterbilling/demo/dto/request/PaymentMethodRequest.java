package com.waterbilling.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PaymentMethodRequest {

    @NotBlank(message = "Không được để trống")
    private String methodName;
    private String description;
}
