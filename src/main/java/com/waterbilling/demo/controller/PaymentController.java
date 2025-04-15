package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.PaymentRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.dto.response.TransactionResponse;
import com.waterbilling.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")

    public ApiResponse<?> processPayment(@RequestBody PaymentRequest request) {
        return ApiResponse.<TransactionResponse>builder()
                .result(paymentService.processPayment(request))
                .build();
    }
}
