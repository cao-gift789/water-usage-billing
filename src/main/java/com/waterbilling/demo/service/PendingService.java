package com.waterbilling.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.request.JoinStatusRequest;
import com.waterbilling.demo.dto.response.HouseHoldResponse;
import com.waterbilling.demo.dto.response.PendingResponse;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.JoinRequest;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.repository.JoinRequestRepository;

@Service
public class PendingService {
	@Autowired
	private JoinRequestRepository joinRequestRepository;

	public List< PendingResponse> findPending(JoinStatusRequest joinStatusRequest) {
		
		List< PendingResponse>houseHoldResponse=new ArrayList<>();
		
		List<JoinRequest> joinRequests= joinRequestRepository.findByStatus(joinStatusRequest.getJoinStatus());
		for(JoinRequest jr:joinRequests) {
			PendingResponse house =new PendingResponse();
			
			User user =jr.getUser();
			Facility facility =jr.getFacility();
			house.setName(user.getFullName());
			house.setFacilityCode(facility.getFacilityId());
			house.setAddress(facility.getAddress());
			house.setMember(facility.getLocationManagers().size());
			
			house.setRequestDate(jr.getRequestDate());
			house.setStatus(jr.getStatus());
			
			houseHoldResponse.add(house);
		}
		
		return houseHoldResponse;
	}
}