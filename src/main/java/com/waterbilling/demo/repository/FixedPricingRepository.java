package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.FixedPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedPricingRepository extends JpaRepository<FixedPricing, Integer> {
}
