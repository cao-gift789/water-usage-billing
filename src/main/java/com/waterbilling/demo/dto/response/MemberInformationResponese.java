package com.waterbilling.demo.dto.response;
import java.util.Set;
public class MemberInformationResponese {

	private String name;
	private Integer facilityCode;
	private String address;
	private String phoneNumber;
	private String email;
	private Integer member;
	private Set<Integer> waterMeterID;
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	
	
	
	
	
}
