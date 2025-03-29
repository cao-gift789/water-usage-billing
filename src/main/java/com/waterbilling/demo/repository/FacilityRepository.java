package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    
}
