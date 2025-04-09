package com.example.demo.repository;


import com.example.demo.model.InvoiceWaterMeterReading;
import com.example.demo.model.InvoiceWaterMeterReadingId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceWaterMeterReadingRepository extends JpaRepository<InvoiceWaterMeterReading,InvoiceWaterMeterReadingId> {
}
