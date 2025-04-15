package com.waterbilling.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterFacilityRequest {

    @NotBlank(message = "Địa chỉ không được để trống")
    String address;

    @NotNull(message = "Loại cơ sở không được để trống")
    @Positive(message = "Giá trị phải lớn hơn 0")
    Integer facilityTypeId;

    @NotBlank(message = "CMND/CCCD không được để trống")
    @Pattern(regexp = "\\d{9}|\\d{12}", message = "CMND/CCCD phải có 9 hoặc 12 chữ số")
    String identityNumber;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "Số điện thoại phải bắt đầu bằng 0 hoặc +84 và có 10 chữ số")
    String phoneNumber;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 100, message = "Họ tên phải từ 2 đến 100 ký tự")
    String fullName;

}
