package com.example.demo.service;

import com.example.demo.model.Facility;
import com.example.demo.model.User;
import com.example.demo.repository.FacilityRepository;
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

    public List<Facility> getFacilitiesByOwner(User facility_user) {
        return facilityRepository.findAll();
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
