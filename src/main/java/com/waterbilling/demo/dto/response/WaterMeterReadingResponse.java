package com.waterbilling.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WaterMeterReadingResponse {

    Integer readingId;
    LocalDateTime dateRecorded;
    BigDecimal previousReading;
    BigDecimal currentReading;
    BigDecimal waterUsage;
    String nameEmployee;
}
