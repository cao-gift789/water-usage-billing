package com.waterbilling.demo.service;

import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public List<Facility> getFacilitiesByOwner(User owner) {
        return facilityRepository.findByOwner(owner);
    }

    public Optional<Facility> getFacilityById(Integer id) {
        return facilityRepository.findById(id);
    }

    public Facility saveFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    public void deleteFacility(Integer id) {
        facilityRepository.deleteById(id);
    }
}
