package com.waterbilling.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.repository.SupportRepository;

@Service
public class SupportService {
	@Autowired
	private SupportRepository supportRepository;
	
	public void saveSupport(Support support) {
		supportRepository.save(support);
	}
}
