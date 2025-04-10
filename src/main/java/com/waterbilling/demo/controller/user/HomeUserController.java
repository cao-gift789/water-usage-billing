package com.waterbilling.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waterbilling.demo.dto.request.JoinStatusRequest;
import com.waterbilling.demo.dto.response.HouseHoldResponse;
import com.waterbilling.demo.dto.response.MemberInformationResponese;
import com.waterbilling.demo.dto.response.NotificationResponse;
import com.waterbilling.demo.enums.JoinStatus;
import com.waterbilling.demo.model.Notification;
import com.waterbilling.demo.repository.NotificationRepository;
import com.waterbilling.demo.service.HouseHoldService;
import com.waterbilling.demo.service.MemberInformationService;
import com.waterbilling.demo.service.NotificationService;
import com.waterbilling.demo.service.PendingService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class HomeUserController {
	
	@Autowired
	private HouseHoldService houseHoldService;
	@Autowired
	private PendingService pendingService;
	@Autowired
	private MemberInformationService memberInfor;
	@Autowired
	private NotificationService notificationService;
	@GetMapping(value="/home")
	public List<Object> home(@Valid @RequestBody JoinStatusRequest joinStatusRequest  ) {
		List<Object> objects=new ArrayList<>();
		if(joinStatusRequest.getJoinStatus() ==JoinStatus.accepted) {
			objects.addAll(houseHoldService.findHouseHold(joinStatusRequest));
		}
		if(joinStatusRequest.getJoinStatus() ==JoinStatus.pending) {
			objects.addAll(pendingService.findPending(joinStatusRequest));
		}
		return objects;
	}
	
	@GetMapping(value="/{id}")
	public MemberInformationResponese house(@PathVariable("id") Integer id) {
		return memberInfor.findInformation(id);
	}
	@GetMapping(value="/{id}/thong-bao")
	public List<NotificationResponse> notification(@PathVariable("id") Integer id) {
		return notificationService.findNotification(id);
	}
	
	
	
}
