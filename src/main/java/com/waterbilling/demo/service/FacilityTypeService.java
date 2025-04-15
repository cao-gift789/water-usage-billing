package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.FacilityTypeRequest;
import com.waterbilling.demo.dto.request.PricingTierRequest;
import com.waterbilling.demo.dto.response.FacilityTypeResponse;
import com.waterbilling.demo.dto.response.PricingTierResponse;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.model.FixedPricing;
import com.waterbilling.demo.model.PricingTiers;
import com.waterbilling.demo.repository.FacilityTypeRepository;
import com.waterbilling.demo.repository.FixedPricingRepository;
import com.waterbilling.demo.repository.PricingTiersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityTypeService {

    @Autowired
    private FacilityTypeRepository facilityTypeRepository;

    @Autowired
    private FixedPricingRepository fixedPricingRepository;

    @Autowired
    private PricingTiersRepository pricingTiersRepository;




    public List<FacilityTypeResponse> getAllFacilityTypes() {
        return facilityTypeRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    public FacilityTypeResponse getFacilityTypeById(Integer typeId) {
        FacilityType facilityType = facilityTypeRepository.findById(typeId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_TYPE_NOT_EXIST));
        return mapToResponse(facilityType);
    }

    // Thêm mới FacilityType
    @Transactional
    public FacilityTypeResponse createFacilityType(FacilityTypeRequest request) {

        validateRequest(request);

        FacilityType facilityType = new FacilityType();
        facilityType.setTypeName(request.getTypeName());
        facilityType.setCalculationMethod(FacilityType.CalculationMethod.valueOf(request.getCalculationMethod()));
        facilityType = facilityTypeRepository.save(facilityType);

        if ("Fixed".equals(request.getCalculationMethod())) {
            FixedPricing fixedPricing = new FixedPricing();
            fixedPricing.setFacilityType(facilityType);
            fixedPricing.setPricePerM3(request.getFixedPrice());
            fixedPricingRepository.save(fixedPricing);
        } else {
            for (PricingTierRequest tierRequest : request.getTiers()) {
                PricingTiers pricingTier = new PricingTiers();
                pricingTier.setFacilityType(facilityType);
                pricingTier.setMinUsage(tierRequest.getMinUsage());
                pricingTier.setMaxUsage(tierRequest.getMaxUsage());
                pricingTier.setPricePerM3(tierRequest.getPricePerM3());
                pricingTiersRepository.save(pricingTier);
            }
        }

        return mapToResponse(facilityType);
    }

    // Cập nhật FacilityType
    @Transactional
    public FacilityTypeResponse updateFacilityType(Integer typeId, FacilityTypeRequest request) {
        validateRequest(request);

        FacilityType facilityType = facilityTypeRepository.findById(typeId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_TYPE_NOT_EXIST));

        facilityType.setTypeName(request.getTypeName());
        facilityType.setCalculationMethod(FacilityType.CalculationMethod.valueOf(request.getCalculationMethod()));
        facilityTypeRepository.save(facilityType);


        fixedPricingRepository.deleteByFacilityType(facilityType);
        pricingTiersRepository.deleteAllByFacilityType(facilityType);

        if ("Fixed".equals(request.getCalculationMethod())) {
            FixedPricing fixedPricing = new FixedPricing();
            fixedPricing.setFacilityType(facilityType);
            fixedPricing.setPricePerM3(request.getFixedPrice());
            fixedPricingRepository.save(fixedPricing);
        } else {
            for (PricingTierRequest tierRequest : request.getTiers()) {
                PricingTiers pricingTier = new PricingTiers();
                pricingTier.setFacilityType(facilityType);
                pricingTier.setMinUsage(tierRequest.getMinUsage());
                pricingTier.setMaxUsage(tierRequest.getMaxUsage());
                pricingTier.setPricePerM3(tierRequest.getPricePerM3());
                pricingTiersRepository.save(pricingTier);
            }
        }

        return mapToResponse(facilityType);
    }

    // Xóa FacilityType
    @Transactional
    public void deleteFacilityType(Integer typeId) {
        FacilityType facilityType = facilityTypeRepository.findById(typeId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_TYPE_NOT_EXIST));
        if (!facilityType.getFacilities().isEmpty()){
            throw new AppException(ErrorCode.FACILITY_TYPE_DELETE_ERROR);
        }
        Optional<FixedPricing> fixedPricing = fixedPricingRepository.findByFacilityType(facilityType);

        fixedPricing.ifPresent(pricing -> fixedPricingRepository.delete(pricing));

        List<PricingTiers> tiers = pricingTiersRepository.findAllByFacilityType(facilityType);
        tiers.forEach(
                tier -> pricingTiersRepository.delete(tier)
        );
        facilityTypeRepository.delete(facilityType);
    }


    private  FacilityTypeResponse mapToResponse(FacilityType facilityType) {
        FacilityTypeResponse response = new FacilityTypeResponse();
        response.setTypeId(facilityType.getTypeId());
        response.setTypeName(facilityType.getTypeName());
        response.setCalculationMethod(facilityType.getCalculationMethod().name());

        if (facilityType.getCalculationMethod() == FacilityType.CalculationMethod.Fixed) {
            Optional<FixedPricing> fixedPricing = fixedPricingRepository.findByFacilityType(facilityType);

            fixedPricing.ifPresent(pricing -> response.setFixedPrice(pricing.getPricePerM3()));
        } else {
            List<PricingTiers> tiers = pricingTiersRepository.findAllByFacilityType(facilityType);
            response.setTiers(tiers.stream().map(tier -> {
                PricingTierResponse tierResponse = new PricingTierResponse();
                tierResponse.setTierId(tier.getTierId());
                tierResponse.setMinUsage(tier.getMinUsage());
                tierResponse.setMaxUsage(tier.getMaxUsage());
                tierResponse.setPricePerM3(tier.getPricePerM3());
                return tierResponse;
            }).collect(Collectors.toList()));
        }

        return response;
    }

    // Validation logic
    private void validateRequest(FacilityTypeRequest request) {
        if (!List.of("Fixed", "Tiered").contains(request.getCalculationMethod())) {
            throw new IllegalArgumentException("CalculationMethod phải là Fixed hoặc Tiered");
        }
        if ("Fixed".equals(request.getCalculationMethod())) {
            if (request.getFixedPrice() == null || request.getFixedPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("FixedPrice phải là số dương");
            }
            if (request.getTiers() != null && !request.getTiers().isEmpty()) {
                throw new IllegalArgumentException("Tiers không được cung cấp khi CalculationMethod là Fixed");
            }
        } else {
            if (request.getTiers() == null || request.getTiers().isEmpty()) {
                throw new IllegalArgumentException("Tiers là bắt buộc khi CalculationMethod là Tiered");
            }
            for (PricingTierRequest tier : request.getTiers()) {
                if (tier.getMaxUsage() != null && tier.getMaxUsage().compareTo(tier.getMinUsage()) <= 0) {
                    throw new IllegalArgumentException("MaxUsage phải lớn hơn MinUsage");
                }
            }
            if (request.getFixedPrice() != null) {
                throw new IllegalArgumentException("FixedPrice không được cung cấp khi CalculationMethod là Tiered");
            }
        }
    }
}
