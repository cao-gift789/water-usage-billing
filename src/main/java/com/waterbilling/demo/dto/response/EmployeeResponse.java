package com.waterbilling.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {
    private int employeeId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private String roleName;
    private boolean isActive;  // boolean kiểu true/false

    // Nếu bạn chưa có setter/getter này, hãy thêm vào
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return this.isActive;
    }
}
