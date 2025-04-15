package com.waterbilling.demo.repository;

import java.util.List;
import java.util.Optional;

import com.waterbilling.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, Integer> {
    List<JoinRequest> findAllByFacility(Facility facility);

    List<JoinRequest> findAllByUser(User user);
}
