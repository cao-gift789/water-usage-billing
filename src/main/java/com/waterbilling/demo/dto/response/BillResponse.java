package com.waterbilling.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.waterbilling.demo.model.Invoice.InvoiceStatus;

public class BillResponse {
	private Integer id;
	private Integer householdCode;
	private LocalDateTime period;
	private InvoiceStatus  status;
	private String customerName;
	private Integer customerCode;
	private String address;
	private Set<MetersRespone>meters;
	private BigDecimal totalConsumption;
	private BigDecimal waterCost;
	private LocalDateTime dueDate;
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHouseholdCode() {
		return householdCode;
	}
	public void setHouseholdCode(Integer householdCode) {
		this.householdCode = householdCode;
	}
	public LocalDateTime getPeriod() {
		return period;
	}
	public void setPeriod(LocalDateTime period) {
		this.period = period;
	}
	public InvoiceStatus  getStatus() {
		return status;
	}
	public void setStatus(InvoiceStatus  status) {
		this.status = status;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(Integer customerCode) {
		this.customerCode = customerCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Set<MetersRespone> getMeters() {
		return meters;
	}
	public void setMeters(Set<MetersRespone> meters) {
		this.meters = meters;
	}
	public BigDecimal getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(BigDecimal totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	public BigDecimal getWaterCost() {
		return waterCost;
	}
	public void setWaterCost(BigDecimal waterCost) {
		this.waterCost = waterCost;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
	
	
	
	
	
}
