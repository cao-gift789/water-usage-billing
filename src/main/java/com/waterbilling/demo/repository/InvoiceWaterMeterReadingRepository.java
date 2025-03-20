package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.InvoiceWaterMeterReading;
import com.waterbilling.demo.model.InvoiceWaterMeterReadingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceWaterMeterReadingRepository extends JpaRepository<InvoiceWaterMeterReading, InvoiceWaterMeterReadingId> {
}
