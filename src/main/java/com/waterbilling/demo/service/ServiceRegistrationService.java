package com.waterbilling.demo.service;

import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.dto.request.ServiceRegistrationRequest;
import com.waterbilling.demo.model.ServiceRegistration;
import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.repository.ServiceRegistrationRepository;

import java.util.List;

@Service
public class ServiceRegistrationService {
	@Autowired
	private ServiceRegistrationRepository serviceRegistrationRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ServiceRegistration saveServiceRegistration(ServiceRegistrationRequest request) {
		return serviceRegistrationRepository.save(modelMapper.map(request, ServiceRegistration.class));
	}
	public List<ServiceRegistration> findALl(){
		return serviceRegistrationRepository.findAll();
	}

	public ServiceRegistration getSupport(Integer id) {
		return serviceRegistrationRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.SUPPORT_INFO_NOT_EXISTED));
	}

	public void deleteSupport(Integer id){
		serviceRegistrationRepository.deleteById(id);
	}
}
