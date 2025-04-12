package com.waterbilling.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.response.InvoiceDetailResponse;
import com.waterbilling.demo.dto.response.MetersRespone;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.Invoice;
import com.waterbilling.demo.model.Invoice.InvoiceStatus;
import com.waterbilling.demo.model.Transaction;
import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.model.WaterMeterReading;
import com.waterbilling.demo.repository.FacilityRepository;
import com.waterbilling.demo.repository.InvoiceRepository;

@Service
public class InvoiceService {
	
	
	@Autowired
	private FacilityRepository facilityRepository;
	
	  
	
	public List<InvoiceDetailResponse> findInvoice(Integer facilityid,Integer check ) {
		
		List<InvoiceDetailResponse> billResponse =new ArrayList<>();
		
		
		
		
		Facility facility = facilityRepository.findById(facilityid)
			    .orElseThrow(() -> new RuntimeException("Facility not found"));
		
		Set<Invoice> invoices = facility.getInvoices();
		for(Invoice in:invoices ) {
			if(check==0) {
			if(in.getStatus()==InvoiceStatus.unpaid) {
			InvoiceDetailResponse it=new InvoiceDetailResponse();
			it.setId(in.getInvoiceId());
			it.setHouseholdCode(facilityid);
			it.setPeriod(in.getCreationDate());
			it.setStatus(in.getStatus());
			it.setCustomerName(in.getUser().getFullName());
			it.setCustomerCode(in.getUser().getUserId());
			it.setMethodName(null);
			
			Set<MetersRespone> metersRespones =new HashSet<>() ;
			
			BigDecimal a=new BigDecimal("0");
			for(WaterMeterReading waterMeterReading :in.getWaterMeterReadings()) {
				MetersRespone mt =new MetersRespone();
				mt.setMeterCode(waterMeterReading.getWaterMeter().getWaterMeterId());
//				mt.setOldIndex(waterMeterReading.getPreviousReading());
//				mt.setNewIndex(waterMeterReading.getCurrentReading());
//				mt.setConsumption(waterMeter  Reading.getWaterUsage());
				
				a=a.add(waterMeterReading.getWaterUsage());
				
				metersRespones.add(mt);
			}
			it.setMeters(metersRespones);
			it.setTotalConsumption(a);
			it.setWaterCost(in.getTotalAmount());
	//		it.setDueDate(in.getGracePeriod());
			
			billResponse.add(it);
			}
			}
			else {
				InvoiceDetailResponse it=new InvoiceDetailResponse();
				it.setId(in.getInvoiceId());
				it.setHouseholdCode(facilityid);
				it.setPeriod(in.getCreationDate());
				it.setStatus(in.getStatus());
				it.setCustomerName(in.getUser().getFullName());
				it.setCustomerCode(in.getUser().getUserId());
				
				Set<String>s=new HashSet<>();
				for(Transaction transaction:in.getTransactions()) {
					s.add(transaction.getPaymentMethod().getMethodName());
				}
				it.setMethodName(s);
				
				Set<MetersRespone> metersRespones =new HashSet<>() ;
				
				BigDecimal a=new BigDecimal("0");
				for(WaterMeterReading waterMeterReading :in.getWaterMeterReadings()) {
					MetersRespone mt =new MetersRespone();
					mt.setMeterCode(waterMeterReading.getWaterMeter().getWaterMeterId());
//					mt.setOldIndex(waterMeterReading.getPreviousReading());
//					mt.setNewIndex(waterMeterReading.getCurrentReading());
//					mt.setConsumption(waterMeter  Reading.getWaterUsage());
					
					a=a.add(waterMeterReading.getWaterUsage());
					
					metersRespones.add(mt);
				}
				it.setMeters(metersRespones);
				it.setTotalConsumption(a);
				it.setWaterCost(in.getTotalAmount());
		//		it.setDueDate(in.getGracePeriod());
				
				billResponse.add(it);
			}
			
			
		}
		
		return billResponse;
	}
}
