package com.waterbilling.demo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacilityService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> getAllFacilities() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_get_all_facilities");
        return query.getResultList();
    }

    public void addFacility(String address, LocalDateTime registrationDate, int ownerId, int facilityTypeId) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_add_facility");

        query.registerStoredProcedureParameter("p_address", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_registrationDate", LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_ownerId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_facilityTypeId", Integer.class, ParameterMode.IN);

        query.setParameter("p_address", address);
        query.setParameter("p_registrationDate", registrationDate);
        query.setParameter("p_ownerId", ownerId);
        query.setParameter("p_facilityTypeId", facilityTypeId);

        query.execute();
    }

    public void deactivateFacility(int facilityId) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_deactivate_facility");

        query.registerStoredProcedureParameter("p_facilityId", Integer.class, ParameterMode.IN);
        query.setParameter("p_facilityId", facilityId);
        query.execute();
    }
}
