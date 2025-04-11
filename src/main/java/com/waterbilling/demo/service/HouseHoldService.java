package com.waterbilling.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.request.JoinStatusRequest;
import com.waterbilling.demo.dto.response.HouseHoldResponse;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.JoinRequest;
import com.waterbilling.demo.model.LocationManager;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.repository.JoinRequestRepository;

@Service
public class HouseHoldService {
	@Autowired
	private JoinRequestRepository joinRequestRepository;
	public List< HouseHoldResponse> findHouseHold(JoinStatusRequest joinStatusRequest) {
		
		List< HouseHoldResponse>houseHoldResponse=new ArrayList<>();
		
		List<JoinRequest> joinRequests= joinRequestRepository.findByStatus(joinStatusRequest.getJoinStatus());
		for(JoinRequest jr:joinRequests) {
			HouseHoldResponse house =new HouseHoldResponse();
			
			User user =jr.getUser();
			Facility facility =jr.getFacility();
			house.setName(user.getFullName());
			house.setFacilityCode(facility.getFacilityId());
			house.setAddress(facility.getAddress());
			house.setMember(facility.getLocationManagers().size());
			
			Set<Integer>a=new HashSet<>();
			for(WaterMeter w:facility.getWaterMeters()) {
				a.add(w.getWaterMeterId());
			}
			
			house.setWaterMeterID(a);
			house.setTypeName(facility.getFacilityType().getTypeName());
			
			
			houseHoldResponse.add(house);
		}
		
		return houseHoldResponse;
	}
}
