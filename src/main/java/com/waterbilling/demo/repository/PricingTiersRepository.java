package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.model.FixedPricing;
import com.waterbilling.demo.model.PricingTiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PricingTiersRepository extends JpaRepository<PricingTiers, Integer> {

    void deleteAllByFacilityType(FacilityType facilityType);

    List<PricingTiers> findAllByFacilityType(FacilityType facilityType);
}
