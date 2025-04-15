package com.waterbilling.demo.service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import java.time.LocalDateTime;

import com.waterbilling.demo.dto.response.EmployeeResponse;
import com.waterbilling.demo.model.Employee;
import com.waterbilling.demo.repository.EmployeeRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class EmployeeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> getAllEmployees() {
        return entityManager.createStoredProcedureQuery("sp_get_all_employees").getResultList();
    }

    public void addEmployeeWithAccount(String username, String password, int roleId,
                                       String fullName, String phone, String email,
                                       String address, LocalDateTime startDate, String image) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_add_employee_with_account");
        query.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_roleId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_fullName", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_phone", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_address", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_startDate", LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_image", String.class, ParameterMode.IN);

        query.setParameter("p_username", username);
        query.setParameter("p_password", password);
        query.setParameter("p_roleId", roleId);
        query.setParameter("p_fullName", fullName);
        query.setParameter("p_phone", phone);
        query.setParameter("p_email", email);
        query.setParameter("p_address", address);
        query.setParameter("p_startDate", startDate);
        query.setParameter("p_image", image);

        query.execute();
    }

    public void updatePassword(int accountId, String newPassword) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_update_account_password");
        query.registerStoredProcedureParameter("p_accountId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_newPassword", String.class, ParameterMode.IN);

        query.setParameter("p_accountId", accountId);
        query.setParameter("p_newPassword", newPassword);

        query.execute();
    }

    public void deactivateEmployee(int employeeId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_deactivate_employee");
        query.registerStoredProcedureParameter("p_employeeId", Integer.class, ParameterMode.IN);
        query.setParameter("p_employeeId", employeeId);
        query.execute();
    }

    public void setAccountRole(int accountId, int roleId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_set_account_role");
        query.registerStoredProcedureParameter("p_accountId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_roleId", Integer.class, ParameterMode.IN);

        query.setParameter("p_accountId", accountId);
        query.setParameter("p_roleId", roleId);

        query.execute();
    }

    public void createWaterMeter(String serialNumber, Integer facilityId, LocalDateTime installationDate) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_create_water_meter");

        query.registerStoredProcedureParameter("p_serialNumber", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_facilityId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_installationDate", LocalDateTime.class, ParameterMode.IN);

        query.setParameter("p_serialNumber", serialNumber);
        query.setParameter("p_facilityId", facilityId);
        query.setParameter("p_installationDate", installationDate);

        query.execute();
    }

}
