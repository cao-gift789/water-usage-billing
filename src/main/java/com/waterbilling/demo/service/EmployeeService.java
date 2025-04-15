package com.waterbilling.demo.service;

import com.waterbilling.demo.model.Employee;
import com.waterbilling.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {

        return employeeRepository.save(employee);
    }

    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee update(Integer id, Employee employee) {
        Employee existing = findById(id);

        existing.setFullName(employee.getFullName());
        existing.setPhoneNumber(employee.getPhoneNumber());
        existing.setAddress(employee.getAddress());
        existing.setEmail(employee.getEmail());
        existing.setAccount(employee.getAccount());
        existing.setStartDate(employee.getStartDate());
        existing.setIsActive(employee.getIsActive());
        existing.setImage(employee.getImage());
        return employeeRepository.save(existing);
    }

    public void delete(Integer id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
    }
}
