package com.waterbilling.demo.service;

import com.waterbilling.demo.model.CustomerType;
import com.waterbilling.demo.repository.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    public List<CustomerType> getAllCustomerTypes() {
        return customerTypeRepository.findAll();
    }

    public CustomerType getCustomerTypeByName(String typeName) {
        return customerTypeRepository.findByTypeName(typeName);
    }

    public CustomerType saveCustomerType(CustomerType customerType) {
        return customerTypeRepository.save(customerType);
    }
}
