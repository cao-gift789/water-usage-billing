package com.waterbilling.demo.controller;

import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public Optional<Facility> getFacilityById(@PathVariable Integer id) {
        return facilityService.getFacilityById(id);
    }

    @PostMapping
    public Facility createFacility(@RequestBody Facility facility) {
        return facilityService.saveFacility(facility);
    }

    @DeleteMapping("/{id}")
    public void deleteFacility(@PathVariable Integer id) {
        facilityService.deleteFacility(id);
    }
}
