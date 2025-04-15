package com.waterbilling.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityUpdateRequest {

    @NotBlank(message = "Địa chỉ không được để trống")
    String address;

    @NotNull(message = "FacilityTypeId không được để trống")
    @Positive(message = "Id phải là số dương")
    Integer facilityTypeId;
}
