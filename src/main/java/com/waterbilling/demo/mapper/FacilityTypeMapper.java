//package com.waterbilling.demo.mapper;
//
//import com.waterbilling.demo.dto.response.FacilityTypeResponse;
//import com.waterbilling.demo.dto.response.PricingTierResponse;
//import com.waterbilling.demo.model.FacilityType;
//import com.waterbilling.demo.model.FixedPricing;
//import com.waterbilling.demo.model.PricingTiers;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface FacilityTypeMapper {
//
//    PricingTierResponse toPricingTierResponse(PricingTiers pricingTier);
//
//    List<PricingTierResponse> toPricingTierResponseList(List<PricingTiers> pricingTiers);
//
//    @Mapping(source = "calculationMethod", target = "calculationMethod", qualifiedByName = "enumToString")
//    @Mapping(target = "fixedPrice", expression = "java(fixedPricing != null ? fixedPricing.getPricePerM3() : null)")
//    @Mapping(target = "tiers", source = "pricingTiers")
//    FacilityTypeResponse toFacilityTypeResponse(FacilityType facilityType, FixedPricing fixedPricing, List<PricingTiers> pricingTiers);
//
//    List<FacilityTypeResponse> toFacilityTypeResponseDTOList(List<FacilityType> facilityTypes);
//
//    @Named("enumToString")
//    default String enumToString(FacilityType.CalculationMethod calculationMethod) {
//        return calculationMethod != null ? calculationMethod.name() : null;
//    }
//}
