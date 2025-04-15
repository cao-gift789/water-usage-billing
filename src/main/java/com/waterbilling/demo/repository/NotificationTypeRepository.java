package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer> {

    Optional<NotificationType> findByTypeName(String type);
}
