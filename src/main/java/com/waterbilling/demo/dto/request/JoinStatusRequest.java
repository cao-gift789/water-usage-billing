package com.waterbilling.demo.dto.request;

import com.waterbilling.demo.enums.JoinStatus;
import com.waterbilling.demo.model.JoinRequest;

public class JoinStatusRequest {
	private JoinStatus joinStatus;

	public JoinStatus getJoinStatus() {
		return joinStatus;
	}

	public void setJoinStatus(JoinStatus joinStatus) {
		this.joinStatus = joinStatus;
	}
}
