package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.ServiceRegistrationRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.model.ServiceRegistration;
import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.service.ServiceRegistrationService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-registration")
public class ServiceRegistrationController {

    @Autowired
    private ServiceRegistrationService serviceRegistrationService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ApiResponse<?> create(@Valid @RequestBody ServiceRegistrationRequest request) {
        return ApiResponse.<ServiceRegistration>builder()
                .result(serviceRegistrationService.saveServiceRegistration(request))
                .build();

    }
    @GetMapping
    public ApiResponse<?> getAllServiceRegistration() {
        return ApiResponse.<List<ServiceRegistration>>builder()
                .result(serviceRegistrationService.findALl())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getServiceRegistration(@PathVariable("id") Integer id) {
        return ApiResponse.<ServiceRegistration>builder()
                .result(serviceRegistrationService.getSupport(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable("id") Integer id) {
        serviceRegistrationService.deleteSupport(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
