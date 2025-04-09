package com.example.demo.repository;


import com.example.demo.model.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<FacilityType, Integer> {
    FacilityType findByTypeName(String typeName);
}
