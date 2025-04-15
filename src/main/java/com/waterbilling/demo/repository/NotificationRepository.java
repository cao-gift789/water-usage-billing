package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
