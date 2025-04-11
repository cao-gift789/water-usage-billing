package com.waterbilling.demo.service;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.response.HouseHoldResponse;
import com.waterbilling.demo.dto.response.MemberInformationResponese;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.JoinRequest;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.repository.FacilityRepository;
import com.waterbilling.demo.repository.JoinRequestRepository;

@Service
public class MemberInformationService {
	@Autowired
	private FacilityRepository facilityRepository;
	
	public MemberInformationResponese findInformation(Integer id) {
		MemberInformationResponese infor =new MemberInformationResponese();
		
		Facility facility= facilityRepository.getById(id);
		
		infor.setName(facility.getUser().getFullName());
		infor.setFacilityCode(facility.getFacilityId());
		infor.setEmail(facility.getAddress());
		infor.setPhoneNumber(facility.getUser().getPhoneNumber());
		infor.setEmail(facility.getUser().getEmail());
		
		Set<Integer>a=new HashSet<>();
		Set<WaterMeter>waterMeters=facility.getWaterMeters();
		for(WaterMeter w: waterMeters) {
			a.add(w.getWaterMeterId());
		}
		infor.setWaterMeterID(a);
		infor.setMember(facility.getLocationManagers().size());
		
		
		return infor;
		
	}
}
