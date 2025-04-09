package com.waterbilling.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterbilling.demo.model.Support;
@Repository
public interface SupportRepository extends JpaRepository<Support, Integer> {
	
}
