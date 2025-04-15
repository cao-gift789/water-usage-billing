package com.waterbilling.demo.dto.request;

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
public class SupportRequest {

	@Size(max = 100, message = "Họ và tên không được quá 100 ký tự")
	@NotBlank(message = "Tên không được để trống")
	private String name;

	@NotBlank(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "^(0[1-9]{1}[0-9]{8})$", message = "Số điện thoại không đúng định dạng (ví dụ: 0123456789)")
	private String phoneNumber;

	@NotBlank(message = "Câu hỏi không được để trống")
	private String question;

	
}
