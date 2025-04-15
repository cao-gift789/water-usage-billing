package com.waterbilling.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityDetailResponse {

    Integer facilityId;
    String address;
    LocalDateTime registrationDate;
    String nameOwner;
    FacilityTypeResponse facilityType;
    List<InvoiceResponse> invoices;
    List<NotificationResponse> notifications;
    List<WaterMeterResponse> waterMeters;
    Map<Integer,String> members;
    List<JoinRequestOfFacilityResponse> joinRequests;
}
