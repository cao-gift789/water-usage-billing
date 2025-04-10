package com.waterbilling.demo.service;

import java.util.ArrayList;
import java.util.List;

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
	public List<NotificationResponse> findNotification(Integer facilitiid){
		List<NotificationResponse> notificationResponses =new ArrayList<>();
		Facility facility=facilityRepository.getById(facilitiid);
		for(NotificationFacility notificationFacility: facility.getNotificationFacilities()) {
			NotificationResponse nf =new NotificationResponse();
			nf.setStatus(notificationFacility.getStatus());
			nf.setCreatedDate(notificationFacility.getNotification().getCreatedDate());
			nf.setConten(notificationFacility.getNotification().getContent());
			nf.setTypeName(notificationFacility.getNotification().getNotificationType().getTypeName());
			notificationResponses.add(nf);
		}
		return notificationResponses;
	}
}
