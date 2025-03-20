package com.waterbilling.demo.controller;

import com.waterbilling.demo.model.CustomerType;
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
    public List<CustomerType> getAllCustomerTypes() {
        return customerTypeService.getAllCustomerTypes();
    }

    @PostMapping
    public CustomerType createCustomerType(@RequestBody CustomerType customerType) {
        return customerTypeService.saveCustomerType(customerType);
    }
}
