package com.waterbilling.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.request.ServiceRegistrationRequest;
import com.waterbilling.demo.model.ServiceRegistration;
import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.repository.ServiceRegistrationRepository;

@Service
public class ServiceRegistrationService {
	@Autowired
	private ServiceRegistrationRepository serviceRegistrationRepository;

	public void saveServiceRegistration(ServiceRegistration serviceRegistration) {
		serviceRegistrationRepository.save(serviceRegistration);
	}
	
}
