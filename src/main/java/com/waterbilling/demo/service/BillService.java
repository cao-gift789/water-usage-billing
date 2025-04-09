package com.waterbilling.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.response.BillResponse;
import com.waterbilling.demo.dto.response.MetersRespone;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.Invoice;
import com.waterbilling.demo.model.Invoice.InvoiceStatus;
import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.model.WaterMeterReading;
import com.waterbilling.demo.repository.FacilityRepository;
import com.waterbilling.demo.repository.InvoiceRepository;

@Service
public class BillService {
	
	@Autowired
	private FacilityService facilityService;
	@Autowired
	private FacilityRepository facilityRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public List<BillResponse> findBillResponses(Integer facilityid) {
		
		List<BillResponse> billResponse =new ArrayList<>();
		
		
		
		
		Facility facility = facilityRepository.findById(facilityid)
			    .orElseThrow(() -> new RuntimeException("Facility not found"));
		
		List<Invoice> invoices = facility.getFacility_invoice();
		for(Invoice item:invoices) {
			if(item.getStatus() == InvoiceStatus.unpaid) {
			BillResponse it=new BillResponse();
			it.setId(item.getInvoiceId());
			it.setHouseholdCode(facilityid);
			it.setPeriod(item.getCreationDate());
			it.setStatus(item.getStatus());
			it.setCustomerName(item.getInvoice_user().getFullName());
			it.setCustomerCode(item.getInvoice_user().getUserId());
			it.setAddress(item.getInvoice_user().getAddress());
			
			List<MetersRespone> metersRespones =new ArrayList<>() ;
			
			BigDecimal a=new BigDecimal("0");
			for(WaterMeterReading waterMeterReading :item.getInvoice_waterMeterReading()) {
				MetersRespone mt =new MetersRespone();
				mt.setMeterCode(waterMeterReading.getWaterMeterReading_waterMeter().getWaterMeterId());
//				mt.setOldIndex(waterMeterReading.getPreviousReading());
//				mt.setNewIndex(waterMeterReading.getCurrentReading());
//				mt.setConsumption(waterMeter  Reading.getWaterUsage());
				
				a=a.add(waterMeterReading.getWaterUsage());
				
				metersRespones.add(mt);
			}
			it.setMeters(metersRespones);
			it.setTotalConsumption(a);
			it.setWaterCost(item.getTotalAmount());
			it.setDueDate(item.getGracePeriod());
			
			billResponse.add(it);
			}
			
		}
		
		return billResponse;
	}
}
