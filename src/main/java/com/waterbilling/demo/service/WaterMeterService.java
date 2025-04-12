
package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.response.ListWaterUsageResponse;
import com.waterbilling.demo.dto.response.WaterUsageResponse;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.WaterMeter;
import com.waterbilling.demo.model.WaterMeterReading;
import com.waterbilling.demo.repository.FacilityRepository;
import com.waterbilling.demo.repository.WaterMeterRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterMeterService {

    @Autowired
    private WaterMeterRepository waterMeterRepository;
    @Autowired
    private FacilityRepository facilityRepository;
    public WaterMeter saveWaterMeter(WaterMeter waterMeter) {
        return waterMeterRepository.save(waterMeter);
    }
    public List< ListWaterUsageResponse> find(Integer facilityid) {
    	List<ListWaterUsageResponse> list =new ArrayList<>();
    	
    	
    	Facility facility =facilityRepository.getById(facilityid);
    	for(WaterMeter w:facility.getWaterMeters()) {
    		
    		ListWaterUsageResponse listWaterUsageResponse =new ListWaterUsageResponse();
    		
    		Set<WaterUsageResponse> waterUsageResponses=new HashSet<>();
    		BigDecimal total=new BigDecimal("0");    		
    		for(WaterMeterReading wr:w.getWaterMeterReadings()) {
    			WaterUsageResponse water=new WaterUsageResponse();
    			
    			water.setDateRecorded(wr.getDateRecorded());
    			water.setTotalWater(wr.getWaterUsage());
    			
    			waterUsageResponses.add(water);
    			
    			total.add(wr.getWaterUsage());
    		}
    		int n=waterUsageResponses.size();
    		BigDecimal medium= total.divide(new BigDecimal(n),2, RoundingMode.HALF_UP);
    		
    		listWaterUsageResponse.setWaterUsageResponses(waterUsageResponses);
    		listWaterUsageResponse.setMedium(medium);
    		listWaterUsageResponse.setTotal(total);
    		list.add(listWaterUsageResponse);	
    	}
    	return list;
    }
    
}

