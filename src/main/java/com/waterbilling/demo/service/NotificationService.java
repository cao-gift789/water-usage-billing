package com.waterbilling.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.waterbilling.demo.dto.request.NotificationTypeRequest;
import com.waterbilling.demo.model.Notification;
import com.waterbilling.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.response.NotificationResponse;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.NotificationFacility;
import com.waterbilling.demo.repository.FacilityRepository;
@Service
public class NotificationService {
	@Autowired
	private FacilityRepository facilityRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }




}
