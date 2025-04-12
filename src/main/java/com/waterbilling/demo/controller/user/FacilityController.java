package com.waterbilling.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waterbilling.demo.dto.response.MemberInformationResponese;
import com.waterbilling.demo.service.FacilityService;

@Controller
@RequestMapping("/api/user")
public class FacilityController {
	@Autowired
	private  FacilityService facilityService;
	@GetMapping(value="/{id}")
	public MemberInformationResponese house(@PathVariable("id") Integer id) {
		return facilityService.findInformation(id);
	}
	
}
