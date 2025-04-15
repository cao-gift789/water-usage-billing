package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.response.EmployeeResponse;
import com.waterbilling.demo.dto.PasswordRequest;  // Import DTO với Lombok
import com.waterbilling.demo.model.Employee;
import com.waterbilling.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;  // Import Map và HashMap
import java.util.HashMap;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Object[]>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Map<String, Object> req) {
        // ✅ Gỡ ép kiểu trực tiếp gây lỗi
        String username = (String) req.get("username");
        String password = (String) req.get("password");
        Integer roleId = (Integer) req.get("roleId");
        String fullName = (String) req.get("fullName");
        String phone = (String) req.get("phone");
        String email = (String) req.get("email");
        String address = (String) req.get("address");
        String image = (String) req.get("image");
        LocalDateTime startDate = LocalDateTime.parse((String) req.get("startDate"));

        // ✅ Gọi service với tham số đã được xử lý đúng kiểu
        employeeService.addEmployeeWithAccount(
                username,
                password,
                roleId,
                fullName,
                phone,
                email,
                address,
                startDate,
                image
        );

        return ResponseEntity.ok("Thêm nhân viên thành công");
    }


    @PutMapping("/password/{accountId}")
    public ResponseEntity<String> updatePassword(@PathVariable int accountId, @RequestBody Map<String, String> body) {
        employeeService.updatePassword(accountId, body.get("password"));
        return ResponseEntity.ok("Cập nhật mật khẩu thành công");
    }

    @PutMapping("/deactivate/{employeeId}")
    public ResponseEntity<String> deactivate(@PathVariable int employeeId) {
        employeeService.deactivateEmployee(employeeId);
        return ResponseEntity.ok("Đã dừng hoạt động nhân viên");
    }

    @PutMapping("/set-role")
    public ResponseEntity<String> setRole(@RequestBody Map<String, Integer> body) {
        employeeService.setAccountRole(body.get("accountId"), body.get("roleId"));
        return ResponseEntity.ok("Gán quyền thành công");
    }
}