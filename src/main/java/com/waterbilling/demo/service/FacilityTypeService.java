package com.waterbilling.demo.service;

import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.repository.FacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityTypeService {

    @Autowired
    private FacilityTypeRepository facilityTypeRepository;

    public List<FacilityType> getAllCustomerTypes() {
        return facilityTypeRepository.findAll();
    }

    public FacilityType getCustomerTypeByName(String typeName) {
        return facilityTypeRepository.findByTypeName(typeName);
    }

    public FacilityType saveCustomerType(FacilityType customerType) {
        return facilityTypeRepository.save(customerType);
    }
}
