package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.response.InvoiceDetailResponse;
import com.waterbilling.demo.dto.response.InvoiceResponse;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.*;
import com.waterbilling.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {

    @Autowired
    private PricingTiersRepository pricingTiersRepository;

    @Autowired
    private FixedPricingRepository fixedPricingRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private WaterMeterRepository waterMeterRepository;

    @Autowired
    private WaterMeterReadingRepository waterMeterReadingRepository;

    public InvoiceDetailResponse getById(Integer invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_EXIST));

        Facility facility = invoice.getFacility();

        InvoiceDetailResponse dto = new InvoiceDetailResponse();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setFacilityId(facility.getFacilityId());
        dto.setCustomerName(facility.getUser().getFullName());
        dto.setStatus(invoice.getStatus().name());
        dto.setAddress(facility.getAddress());
        dto.setCreationDate(invoice.getCreationDate());
        dto.setPaymentDate(invoice.getPaymentDate());
        dto.setTotalPrice(invoice.getTotalAmount());
        dto.setPenaltyFee(invoice.getPenaltyFee());

        BigDecimal totalComsump = new BigDecimal(0);
        Map<String, BigDecimal> waterMeterReadings = new HashMap<>();
        invoice.getWaterMeterReadings()
                .forEach(wr
                        -> {
                    totalComsump.add(wr.getWaterUsage());
                    waterMeterReadings.put(wr.getWaterMeter().getSerialNumber(), wr.getWaterUsage());
                });
        dto.setTotalConsumption(totalComsump);
        dto.setWaterMeterReadingResponses(waterMeterReadings);

        return dto;
    }

//    public Page<InvoiceResponse> getAllInvoices(String search, String status, Integer facilityId, LocalDateTime creationDate, Pageable pageable) {
//        return invoiceRepository.findAllInvoices(search, status, facilityId, creationDate, pageable);
//    }


    @Transactional
    public void createInvoiceForFacility(Integer facilityId) {

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new IllegalArgumentException("Facility not found with ID: " + facilityId));

        boolean existInvoice = invoiceRepository.existsInvoiceForThisMonth(facilityId);
        if(existInvoice){
            throw new IllegalArgumentException("Hộ này đã có hóa đơn");
        }
        List<WaterMeter> waterMeters = waterMeterRepository.findAllByFacility(facility);
        if (waterMeters.isEmpty()) {
            throw new IllegalArgumentException("No WaterMeters found for Facility ID: " + facilityId);
        }

        // Bước 3: Tính tổng WaterUsage từ WaterMeterReading mới nhất
        BigDecimal totalWaterUsage = BigDecimal.valueOf(0.0);
        for (WaterMeter waterMeter : waterMeters) {
            WaterMeterReading latestReading = waterMeterReadingRepository.findLatestByWaterMeterId(waterMeter.getWaterMeterId());
            if (latestReading != null) {
                totalWaterUsage = totalWaterUsage.add(latestReading.getWaterUsage());
            }
        }

        if (totalWaterUsage.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("No valid WaterMeterReadings found for Facility ID: " + facilityId);
        }

        FacilityType facilityType = facility.getFacilityType();
        BigDecimal totalAmount = calculateTotalAmount(facilityType, totalWaterUsage);


        Invoice invoice = new Invoice();
        invoice.setTotalAmount(totalAmount);
        invoice.setStatus(Invoice.InvoiceStatus.unpaid);
        invoice.setFacility(facility);
        invoice.setCreationDate(LocalDateTime.now());
        invoice.setPenaltyFee(BigDecimal.valueOf(0.0));

        Invoice savedInvoice = invoiceRepository.save(invoice);

        for (WaterMeter waterMeter : waterMeters) {
            WaterMeterReading latestReading = waterMeterReadingRepository.findLatestByWaterMeterId(waterMeter.getWaterMeterId());
            if (latestReading != null) {
                latestReading.setInvoice(savedInvoice);
                waterMeterReadingRepository.save(latestReading);
            }
        }

    }

    private BigDecimal calculateTotalAmount(FacilityType facilityType, BigDecimal waterUsage) {
        if ("Fixed".equals(facilityType.getCalculationMethod())) {
            FixedPricing fixedPricing = fixedPricingRepository.findByFacilityType(facilityType)
                    .orElseThrow(() -> new IllegalStateException("No FixedPricing found for FacilityType ID: " + facilityType.getTypeId()));

            return waterUsage.multiply(fixedPricing.getPricePerM3());
        } else if ("Tiered".equals(facilityType.getCalculationMethod())) {
            List<PricingTiers> tiers = pricingTiersRepository.findAllByFacilityType(facilityType);
            if (tiers.isEmpty()) {
                throw new IllegalStateException("No PricingTiers found for FacilityType ID: " + facilityType.getTypeId());
            }

            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal remainingUsage = waterUsage;

            for (PricingTiers tier : tiers) {
                if (remainingUsage.compareTo(BigDecimal.ZERO) <= 0) break;

                BigDecimal tierUsage = calculateTierUsage(tier, remainingUsage);
                BigDecimal tierCost = tierUsage.multiply(tier.getPricePerM3());
                totalAmount = totalAmount.add(tierCost);
                remainingUsage = remainingUsage.subtract(tierUsage);
            }

            return totalAmount;
        } else {
            throw new IllegalStateException("Invalid CalculationMethod: " + facilityType.getCalculationMethod());
        }
    }

    private BigDecimal calculateTierUsage(PricingTiers tier, BigDecimal remainingUsage) {
        BigDecimal maxUsage = tier.getMaxUsage() != null ? tier.getMaxUsage() : BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal tierRange = maxUsage.subtract(tier.getMinUsage());

        return remainingUsage.min(tierRange);
    }





}
