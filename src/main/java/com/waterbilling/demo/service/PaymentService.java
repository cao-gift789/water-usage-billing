package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.PaymentRequest;
import com.waterbilling.demo.dto.response.PaymentMethodResponse;
import com.waterbilling.demo.dto.response.TransactionResponse;
import com.waterbilling.demo.model.Invoice;
import com.waterbilling.demo.model.PaymentMethod;
import com.waterbilling.demo.model.Transaction;
import com.waterbilling.demo.repository.InvoiceRepository;
import com.waterbilling.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PaymentMethodService paymentMethodService; // Tích hợp với PaymentMethodService

    @Transactional
    public TransactionResponse processPayment(PaymentRequest request) {

        Invoice invoice = invoiceRepository.findById(request.getInvoiceID())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (invoice.getStatus() == Invoice.InvoiceStatus.paid) {
            throw new RuntimeException("Invoice already paid");
        }

        if (request.getAmount().compareTo(invoice.getTotalAmount()) != 0) {
            throw new RuntimeException("Payment amount does not match invoice total");
        }


        PaymentMethodResponse paymentMethodDTO = paymentMethodService.getPaymentMethodById(request.getPaymentMethodID());
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodId(paymentMethodDTO.getPaymentMethodID());
        paymentMethod.setMethodName(paymentMethodDTO.getMethodName());
        paymentMethod.setDescription(paymentMethodDTO.getDescription());


        Transaction transaction = new Transaction();
        transaction.setInvoice(invoice);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setAmount(request.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setReferenceCode(UUID.randomUUID().toString());


        String methodName = paymentMethod.getMethodName().toLowerCase();
        boolean paymentSuccess;
        String message;

        switch (methodName) {
            case "bank transfer":
                paymentSuccess = simulateBankTransfer(request.getAdditionalInfo());
                message = paymentSuccess ? "Bank transfer successful" : "Bank transfer failed";
                break;
            case "mobile wallet":
                paymentSuccess = simulateMobileWallet(request.getAdditionalInfo());
                message = paymentSuccess ? "Mobile wallet payment successful" : "Mobile wallet payment failed";
                break;
            case "cash":
                paymentSuccess = simulateCashPayment();
                message = paymentSuccess ? "Cash payment successful" : "Cash payment failed";
                break;
            default:
                paymentSuccess = simulateDefaultPayment();
                message = paymentSuccess ? "Payment successful" : "Payment failed";
        }

        if (paymentSuccess) {
            transaction.setStatus(Transaction.TransactionStatus.completed);
            invoice.setStatus(Invoice.InvoiceStatus.paid);
            invoice.setPaymentDate(LocalDateTime.now());
        } else {
            transaction.setStatus(Transaction.TransactionStatus.failed);
        }

        transactionRepository.save(transaction);
        invoiceRepository.save(invoice);
        // Tạo response
        TransactionResponse response = new TransactionResponse();
        response.setTransactionID(transaction.getTransactionId());
        response.setInvoiceID(invoice.getInvoiceId());
        response.setPaymentMethodName(paymentMethod.getMethodName());
        response.setAmount(transaction.getAmount());
        response.setStatus(transaction.getStatus().name());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setReferenceCode(transaction.getReferenceCode());
        response.setMessage(message);

        return response;

    }

    private boolean simulateBankTransfer(String additionalInfo) {
        if (additionalInfo == null || additionalInfo.isEmpty()) {
            return false;
        }
        return new Random().nextInt(100) < 70;
    }

    private boolean simulateMobileWallet(String additionalInfo) {
        if (additionalInfo == null || additionalInfo.isEmpty()) {
            return false;
        }
        return new Random().nextInt(100) < 85;
    }

    private boolean simulateCashPayment() {
        return new Random().nextInt(100) < 90;
    }

    private boolean simulateDefaultPayment() {
        return new Random().nextInt(100) < 80;
    }
}
