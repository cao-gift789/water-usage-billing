package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.PaymentMethodRequest;
import com.waterbilling.demo.dto.response.PaymentMethodResponse;
import com.waterbilling.demo.model.PaymentMethod;
import com.waterbilling.demo.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethodResponse> getAllPaymentMethods() {
        return paymentMethodRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaymentMethodResponse getPaymentMethodById(Integer id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not found"));
        return convertToDTO(paymentMethod);
    }

    public PaymentMethodResponse createPaymentMethod(PaymentMethodRequest dto) {
        if (paymentMethodRepository.existsByMethodName(dto.getMethodName())) {
            throw new RuntimeException("Payment method name already exists");
        }
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethodName(dto.getMethodName());
        paymentMethod.setDescription(dto.getDescription());
        PaymentMethod saved = paymentMethodRepository.save(paymentMethod);
        return convertToDTO(saved);
    }

    public PaymentMethodResponse updatePaymentMethod(Integer id, PaymentMethodRequest dto) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not found"));
        if (!paymentMethod.getMethodName().equals(dto.getMethodName()) &&
                paymentMethodRepository.existsByMethodName(dto.getMethodName())) {
            throw new RuntimeException("Payment method name already exists");
        }
        paymentMethod.setMethodName(dto.getMethodName());
        paymentMethod.setDescription(dto.getDescription());
        PaymentMethod updated = paymentMethodRepository.save(paymentMethod);
        return convertToDTO(updated);
    }

    public void deletePaymentMethod(Integer id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment method not found"));
        paymentMethodRepository.delete(paymentMethod);
    }

    private PaymentMethodResponse convertToDTO(PaymentMethod paymentMethod) {
        PaymentMethodResponse dto = new PaymentMethodResponse();
        dto.setPaymentMethodID(paymentMethod.getPaymentMethodId());
        dto.setMethodName(paymentMethod.getMethodName());
        dto.setDescription(paymentMethod.getDescription());
        return dto;
    }
}
