package com.example.demo.repository;


import com.example.demo.model.PricingTiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingTiersRepository extends JpaRepository<PricingTiers, Integer> {
}
