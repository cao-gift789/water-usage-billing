package com.waterbilling.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waterbilling.demo.dto.response.InvoiceDetailResponse;
import com.waterbilling.demo.model.Invoice;
import com.waterbilling.demo.model.Invoice.InvoiceStatus;
import com.waterbilling.demo.service.InvoiceService;

@Controller
@RequestMapping("/api/user")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceService;
	
	@GetMapping(value="/{id}/invoice")
	List<InvoiceDetailResponse> invoices(@PathVariable("id") Integer facilityID){
		return invoiceService.findInvoice(facilityID,1);

	}
	
}
