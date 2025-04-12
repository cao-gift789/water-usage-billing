package com.waterbilling.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waterbilling.demo.dto.request.JoinStatusRequest;
import com.waterbilling.demo.enums.JoinStatus;
import com.waterbilling.demo.service.JoinRequestService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/user")
	public class HomeUserController {
		
		
		@Autowired	
		private JoinRequestService joinRequestService; 

	
		@GetMapping(value="/home")
		public List<Object> home(@Valid @RequestBody JoinStatusRequest joinStatusRequest  ) {
			List<Object> objects=new ArrayList<>();
			if(joinStatusRequest.getJoinStatus() ==JoinStatus.accepted) {
				objects.addAll(joinRequestService.findHouseHold(joinStatusRequest));
			}
			
			if(joinStatusRequest.getJoinStatus() ==JoinStatus.pending) {
				objects.addAll(joinRequestService.findPending(joinStatusRequest));
			}
			
			return objects;
		}
		
	}


