package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.SupportRequest;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.repository.SupportRepository;

import java.util.List;

@Service
public class SupportService {
	@Autowired
	private SupportRepository supportRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public Support saveSupport(SupportRequest request) {
		return supportRepository.save(modelMapper.map(request, Support.class));
	}

	public List<Support> findALl(){
		return supportRepository.findAll();
	}

	public Support getSupport(Integer id) {
        return supportRepository.findById(id)
                            .orElseThrow(() -> new AppException(ErrorCode.SUPPORT_INFO_NOT_EXISTED));
	}

	public void deleteSupport(Integer id){
		supportRepository.deleteById(id);
	}
}
