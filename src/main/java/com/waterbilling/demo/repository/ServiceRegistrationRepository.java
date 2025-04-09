package com.waterbilling.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waterbilling.demo.model.ServiceRegistration;
import com.waterbilling.demo.model.Support;

public interface ServiceRegistrationRepository extends JpaRepository<ServiceRegistration, Integer>  {

}
