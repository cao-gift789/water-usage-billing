package com.waterbilling.demo.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JoinRequestOfFacilityResponse {

    Integer requestId;
    Integer userId;
    String nameUser;
    LocalDateTime requestDate;

}
