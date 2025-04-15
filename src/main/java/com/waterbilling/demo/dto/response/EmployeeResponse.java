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

    Integer employeeId;
    String fullName;
    String email;

}
