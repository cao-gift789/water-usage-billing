package com.waterbilling.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waterbilling.demo.dto.response.MemberInformationResponese;
import com.waterbilling.demo.dto.response.NotificationResponse;
import com.waterbilling.demo.service.NotificationService;

@Controller
@RequestMapping("/api/user")
public class NotificationController {

	
	@Autowired
	private NotificationService notificationService;
	
	
	@GetMapping(value="/{id}/notification")
	public List<NotificationResponse> notification(@PathVariable("id") Integer facilitiid) {
		return notificationService.findNotification(facilitiid);
	}
	
	
	
}
