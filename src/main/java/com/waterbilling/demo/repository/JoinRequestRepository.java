package com.waterbilling.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterbilling.demo.enums.JoinStatus;
import com.waterbilling.demo.model.Invoice;
import com.waterbilling.demo.model.JoinRequest;
@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, Integer> {
	List<JoinRequest>findByStatus(JoinStatus joinStatus);
}
