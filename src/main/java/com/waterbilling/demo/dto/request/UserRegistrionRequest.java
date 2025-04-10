package com.waterbilling.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrionRequest {

    String identityNumber;
    String email;
    String phoneNumber;
    String fullName;
    String password;
    String username;

}
