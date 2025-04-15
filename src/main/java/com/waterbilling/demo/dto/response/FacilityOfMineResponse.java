package com.waterbilling.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityOfMineResponse {

    Integer facilityId;
    String address;
    LocalDateTime registrationDate;
    LocalDateTime grantedDate;
    Boolean isOwner;
    Boolean isActive;
}
