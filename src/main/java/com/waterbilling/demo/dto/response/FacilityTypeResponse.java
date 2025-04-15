package com.waterbilling.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityTypeResponse {

    Integer typeId;
    String typeName;
    String calculationMethod;
    BigDecimal fixedPrice;
    List<PricingTierResponse> tiers;

}
