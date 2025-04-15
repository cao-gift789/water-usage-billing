package com.waterbilling.demo.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceRegistrationRequest {

	@NotBlank(message = "Họ và tên không được để trống")
	@Size(max = 100, message = "Họ và tên không được quá 100 ký tự")
	String name;

	@NotBlank(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "^(0[1-9]{1}[0-9]{8})$", message = "Số điện thoại không đúng định dạng (ví dụ: 0123456789)")
	String phoneNumber;

	@NotBlank(message = "Email không được để trống")
	@Email(message = "Email không đúng định dạng")
	String email;

	@NotBlank(message = "Loại dịch vụ không được để trống")
	@Size(max = 50, message = "Loại dịch vụ không được quá 50 ký tự")
	String facilityType;

}
