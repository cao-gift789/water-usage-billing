package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    boolean existsByMethodName(String methodName);
}
