package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.Invoice;
import com.waterbilling.demo.model.Invoice.InvoiceStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	

}
