package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.FacilityUpdateRequest;
import com.waterbilling.demo.dto.request.RegisterFacilityRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.dto.response.InvoiceLookupResponse;
import com.waterbilling.demo.dto.response.FacilityDetailResponse;
import com.waterbilling.demo.dto.response.FacilityOfMineResponse;
import com.waterbilling.demo.service.FacilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostMapping
    public ApiResponse<?> addFacility(@Valid @RequestBody RegisterFacilityRequest request) {
        facilityService.createFacilities(request);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping("/{facilityId}")
    public ResponseEntity<String> update
            (@PathVariable("facilityId") int facilityId, @Valid @RequestBody FacilityUpdateRequest request)
    {
        facilityService.updateFacility(facilityId, request);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @PutMapping("/{facilityId}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable("facilityId") int facilityId) {
        facilityService.deactivateFacility(facilityId);
        return ResponseEntity.ok("Hộ gia đình đã được dừng hoạt động");
    }

    @GetMapping(value="/{id}/lookup")
    public ApiResponse<?> lookup(@PathVariable("id") Integer facilityId) {
        return ApiResponse.<InvoiceLookupResponse>builder()
                .result(facilityService.findBillResponses(facilityId))
                .build();
    }
    @GetMapping("/mine")
    public ApiResponse<?> getFacilities() {
        return ApiResponse.<Set<FacilityOfMineResponse>>builder()
                .result(facilityService.getListFacility())
                .build();

    }

    @GetMapping("/{facilityId}")
    public ApiResponse<?> getHouseholdDetails(
            @PathVariable Integer facilityId) {
        return ApiResponse.<FacilityDetailResponse>builder()
                .result(facilityService.getHouseholdDetails(facilityId))
                .build();
    }


}
