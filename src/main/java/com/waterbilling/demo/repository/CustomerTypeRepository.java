package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<FacilityType, Integer> {
    FacilityType findByTypeName(String typeName);
}
