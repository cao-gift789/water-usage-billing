package com.waterbilling.demo.dto.response;

import java.time.LocalDateTime;

import com.waterbilling.demo.model.NotificationFacility.NotificationStatus;



public class NotificationResponse {
	private String conten;
	private LocalDateTime createdDate;
	private NotificationStatus status;
	private String typeName;
	
	public String getConten() {
		return conten;
	}
	public void setConten(String conten) {
		this.conten = conten;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public NotificationStatus getStatus() {
		return status;
	}
	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
	
	
}
