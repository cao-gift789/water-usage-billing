package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.PricingTiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingTiersRepository extends JpaRepository<PricingTiers, Integer> {
}
