package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.LocationManager;
import com.waterbilling.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationManagerRepository extends JpaRepository<LocationManager, Integer> {


    List<LocationManager> findAllByFacility(Facility facility);

    Optional<LocationManager> findAllByUserAndFacility(User user, Facility facility);
}
