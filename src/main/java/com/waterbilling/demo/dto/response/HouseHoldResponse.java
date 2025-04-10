package com.waterbilling.demo.dto.response;

import java.util.Set;

public class HouseHoldResponse {
	private String name;
	private Integer facilityCode;
	private String address;
	private Integer member;
	private Set<Integer> waterMeterID;
	private String typeName;
	
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
	
	public Set<Integer> getWaterMeterID() {
		return waterMeterID;
	}
	public void setWaterMeterID(Set<Integer> waterMeterID) {
		this.waterMeterID = waterMeterID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
			
}
