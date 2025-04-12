package com.waterbilling.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.waterbilling.demo.model.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {

    String fullName;
    String phoneNumber;
    String address;
    String email;
    Account account;
    LocalDateTime startDate;
    Set<News> news =new HashSet<>();
    Set<Notification> notifications =new HashSet<>();
    Set<WaterMeterReading> waterMeterReadings = new HashSet<>();
    Set<Invoice> invoices =new HashSet<>();
    String image;

}
