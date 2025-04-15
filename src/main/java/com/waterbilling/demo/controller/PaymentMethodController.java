package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.PaymentMethodRequest;
import com.waterbilling.demo.dto.response.PaymentMethodResponse;
import com.waterbilling.demo.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public List<PaymentMethodResponse> getAllPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }

    @GetMapping("/{id}")
    public PaymentMethodResponse getPaymentMethodById(@PathVariable Integer id) {
        return paymentMethodService.getPaymentMethodById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodResponse createPaymentMethod(@RequestBody PaymentMethodRequest dto) {
        return paymentMethodService.createPaymentMethod(dto);
    }

    @PutMapping("/{id}")
    public PaymentMethodResponse updatePaymentMethod(@PathVariable Integer id, @RequestBody PaymentMethodRequest dto) {
        return paymentMethodService.updatePaymentMethod(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePaymentMethod(@PathVariable Integer id) {
        paymentMethodService.deletePaymentMethod(id);
    }
}
