package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.model.FixedPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FixedPricingRepository extends JpaRepository<FixedPricing, Integer> {

    void deleteByFacilityType(FacilityType facilityType);

    Optional<FixedPricing> findByFacilityType(FacilityType facilityType);

}
