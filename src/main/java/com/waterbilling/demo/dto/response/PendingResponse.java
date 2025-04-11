package com.waterbilling.demo.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.waterbilling.demo.enums.JoinStatus;

public class PendingResponse {
	private String name;
	private Integer facilityCode;
	private String address;
	private Integer member;
	private LocalDateTime requestDate;
	private JoinStatus status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFacilityCode() {
		return facilityCode;
	}
	public void setFacilityCode(Integer facilityCode) {
		this.facilityCode = facilityCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMember() {
		return member;
	}
	public void setMember(Integer member) {
		this.member = member;
	}
	public LocalDateTime getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}
	public JoinStatus getStatus() {
		return status;
	}
	public void setStatus(JoinStatus status) {
		this.status = status;
	}
	
	
}
