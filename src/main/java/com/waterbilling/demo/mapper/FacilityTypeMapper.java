package com.waterbilling.demo.mapper;

import com.waterbilling.demo.dto.response.FacilityTypeResponse;
import com.waterbilling.demo.dto.response.PricingTierResponse;
import com.waterbilling.demo.model.FacilityType;
import com.waterbilling.demo.model.FixedPricing;
import com.waterbilling.demo.model.PricingTiers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacilityTypeMapper {

    PricingTierResponse toPricingTierResponse(PricingTiers pricingTier);

    List<PricingTierResponse> toPricingTierResponseList(List<PricingTiers> pricingTiers);

    List<FacilityTypeResponse> toFacilityTypeResponseList(List<FacilityType> facilityTypes);

}
