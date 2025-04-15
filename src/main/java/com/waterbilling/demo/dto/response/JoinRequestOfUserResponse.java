package com.waterbilling.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JoinRequestOfUserResponse {

    Integer requestId;
    Integer facilityId;
    LocalDateTime requestDate;
}
