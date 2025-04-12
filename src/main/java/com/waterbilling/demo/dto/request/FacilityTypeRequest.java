package com.waterbilling.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityTypeRequest {

    @NotBlank(message = "TypeName là bắt buộc")
    String typeName;

    @NotNull(message = "CalculationMethod là bắt buộc")
    String calculationMethod;

    @Positive(message = "FixedPrice phải là số dương")
    BigDecimal fixedPrice;

    List<PricingTierRequest> tiers;

}
