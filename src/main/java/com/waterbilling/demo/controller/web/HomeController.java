
package com.waterbilling.demo.controller.web;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.waterbilling.demo.dto.request.ServiceRegistrationRequest;
import com.waterbilling.demo.dto.request.SupportRequest;
import com.waterbilling.demo.dto.response.InvoiceDetailResponse;
import com.waterbilling.demo.model.Invoice.InvoiceStatus;
import com.waterbilling.demo.model.News;
import com.waterbilling.demo.model.ServiceRegistration;
import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.service.InvoiceService;
import com.waterbilling.demo.service.NewsService;
import com.waterbilling.demo.service.ServiceRegistrationService;
import com.waterbilling.demo.service.SupportService;

import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/api")

public class HomeController {

	@Autowired
	private SupportService supportService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private ServiceRegistrationService serviceRegistrationService;
    @Autowired
    private ModelMapper modelMapper;
    
	@GetMapping(value="/home")
	public void home() {
		
	}
	@GetMapping(value="/price-list")
	public void priceList() {
		
	}
	@GetMapping(value="/support")
	public void support(@Valid @RequestBody SupportRequest request) {
		supportService.saveSupport(modelMapper.map(request, Support.class));
	}
	
	@GetMapping(value="/news")
	public List<News>news() {
		return newsService.findNews();
	}
	@GetMapping(value="/lookup")
	public List<Object> lookup(@Valid @RequestBody Integer facilityID) {	
		
		List<InvoiceDetailResponse> detailResponses =invoiceService.findInvoice(facilityID,0);
		if(detailResponses.isEmpty()) {
			StringBuilder stringBuilder=new StringBuilder("Ma ho khong hop le");
			List<StringBuilder> s =new ArrayList<>();
			s.add(stringBuilder);
			
			List<Object> o=new ArrayList<>(s) ;
			return o;
		}
		
		 List<Object> o=new ArrayList<>(invoiceService.findInvoice(facilityID,0)) ;
		 return o;
				
	}
	@GetMapping(value="service-registration")
	public void getMethodName(@Valid @RequestBody ServiceRegistrationRequest request) {
		serviceRegistrationService.saveServiceRegistration(modelMapper.map(request, ServiceRegistration.class));
	}
	
	
}

