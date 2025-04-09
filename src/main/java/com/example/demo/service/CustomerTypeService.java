package com.example.demo.service;


import com.example.demo.model.FacilityType;
import com.example.demo.repository.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    public List<FacilityType> getAllCustomerTypes() {
        return customerTypeRepository.findAll();
    }

    public FacilityType getCustomerTypeByName(String typeName) {
        return customerTypeRepository.findByTypeName(typeName);
    }

    public FacilityType saveCustomerType(FacilityType customerType) {
        return customerTypeRepository.save(customerType);
    }
}
