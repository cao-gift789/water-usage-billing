package com.waterbilling.demo.controller;

import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customertypes")
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService customerTypeService;

    @GetMapping
    public List<FacilityType> getAllCustomerTypes() {
        return customerTypeService.getAllCustomerTypes();
    }

    @PostMapping
    public FacilityType createCustomerType(@RequestBody FacilityType customerType) {
        return customerTypeService.saveCustomerType(customerType);
    }
}
