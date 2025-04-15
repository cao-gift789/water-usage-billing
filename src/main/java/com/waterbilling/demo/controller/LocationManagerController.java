package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.service.LocationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location-manager")
public class LocationManagerController {

    @Autowired
    private LocationManagerService locationManagerService;

    @DeleteMapping("/{facilityId}/{memberId}")
    public ApiResponse<?> deleteMember(@PathVariable("facilityId") Integer facilityId, @PathVariable("memberId") Integer memberId) {
        locationManagerService.deleteMember(facilityId, memberId);
        return ApiResponse.<String>builder()
                .result("xóa thành viên thành công")
                .build();
    }

    @DeleteMapping("/{facilityId}")
    public ApiResponse<?> deleteMember(@PathVariable("facilityId") Integer facilityId) {
        locationManagerService.leave(facilityId);
        return ApiResponse.<String>builder()
                .result("Thoát hộ thành công")
                .build();
    }
}
