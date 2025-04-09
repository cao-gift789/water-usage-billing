package com.example.demo.controllor;


import com.example.demo.model.FacilityType;
import com.example.demo.service.CustomerTypeService;
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
