package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByAccount(Account account);
}
