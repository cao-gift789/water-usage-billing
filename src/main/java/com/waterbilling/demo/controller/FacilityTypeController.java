package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.FacilityTypeRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.dto.response.FacilityTypeResponse;
import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.service.FacilityTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facility-types")
public class FacilityTypeController {

    @Autowired
    private  FacilityTypeService facilityTypeService;


    @GetMapping
    public ApiResponse<?> getAllFacilityTypes() {
        return ApiResponse.<List<FacilityTypeResponse>>builder()
                .result(facilityTypeService.getAllFacilityTypes())
                .build();
    }

    @GetMapping("/{typeId}")
    public ApiResponse<?> getFacilityTypeById(@PathVariable("typeId") Integer typeId) {
        return ApiResponse.<FacilityTypeResponse>builder()
                .result(facilityTypeService.getFacilityTypeById(typeId))
                .build();
    }

    @PostMapping
    public ApiResponse<?> createFacilityType(@Valid @RequestBody FacilityTypeRequest request) {
        return ApiResponse.<FacilityTypeResponse>builder()
                .result(facilityTypeService.createFacilityType(request))
                .build();
    }

    @PutMapping("/{typeId}")
    public ApiResponse<?> updateFacilityType(
            @PathVariable Integer typeId,
            @Valid @RequestBody FacilityTypeRequest request
    ) {
        return ApiResponse.<FacilityTypeResponse>builder()
                .result(facilityTypeService.updateFacilityType(typeId, request))
                .build();
    }

    @DeleteMapping("/{typeId}")
    public ApiResponse<Void> deleteFacilityType(@PathVariable("typeId") Integer typeId) {
        facilityTypeService.deleteFacilityType(typeId);
        return ApiResponse.<Void>builder().build();
    }
}