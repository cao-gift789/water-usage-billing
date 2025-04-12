package com.waterbilling.demo.controller;

import com.waterbilling.demo.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public ResponseEntity<List<Object[]>> getAllFacilities() {
        List<Object[]> result = facilityService.getAllFacilities();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> addFacility(@RequestBody Map<String, Object> req) {
        String address = (String) req.get("address");
        LocalDateTime registrationDate = LocalDateTime.parse((String) req.get("registrationDate"));
        int ownerId = (int) req.get("ownerId");
        int facilityTypeId = (int) req.get("facilityTypeId");

        facilityService.addFacility(address, registrationDate, ownerId, facilityTypeId);
        return ResponseEntity.ok("Thêm hộ gia đình thành công");
    }

    @PutMapping("/{facilityId}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable int facilityId) {
        facilityService.deactivateFacility(facilityId);
        return ResponseEntity.ok("Hộ gia đình đã được dừng hoạt động");
    }
}
