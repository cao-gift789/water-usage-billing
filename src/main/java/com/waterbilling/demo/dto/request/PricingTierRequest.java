package com.waterbilling.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricingTierRequest {
    @NotNull(message = "MinUsage là bắt buộc")
    @Positive(message = "MinUsage phải là số dương")
    BigDecimal minUsage;

    BigDecimal maxUsage;

    @NotNull(message = "PricePerM3 là bắt buộc")
    @Positive(message = "PricePerM3 phải là số dương")
    BigDecimal pricePerM3;

}
