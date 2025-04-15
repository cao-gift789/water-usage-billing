package com.waterbilling.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricingTierResponse {

    Integer tierId;
    BigDecimal minUsage;
    BigDecimal maxUsage;
    BigDecimal pricePerM3;

}
