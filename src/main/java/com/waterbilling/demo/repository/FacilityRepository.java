package com.waterbilling.demo.repository;

import com.waterbilling.demo.dto.response.NotificationResponse;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.Notification;
import com.waterbilling.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {

}
