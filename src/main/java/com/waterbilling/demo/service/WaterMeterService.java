package com.waterbilling.demo.service;

import com.waterbilling.demo.model.WaterMeter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.PersistenceContext;


@Service
public class WaterMeterService {

    @PersistenceContext
    private final EntityManager entityManager;

    public WaterMeterService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Phương thức tạo đồng hồ nước mới
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

    // Phương thức lấy danh sách tất cả đồng hồ nước
    public List getAllWaterMeters() {
        return entityManager.createStoredProcedureQuery("sp_get_all_watermeters", WaterMeter.class).getResultList();
    }

    // Phương thức cập nhật thông tin đồng hồ nước
    public void updateWaterMeter(Integer waterMeterId, String serialNumber, Integer facilityId, LocalDateTime installationDate) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_update_water_meter");
        query.registerStoredProcedureParameter("p_waterMeterId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_serialNumber", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_facilityId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_installationDate", LocalDateTime.class, ParameterMode.IN);

        query.setParameter("p_waterMeterId", waterMeterId);
        query.setParameter("p_serialNumber", serialNumber);
        query.setParameter("p_facilityId", facilityId);
        query.setParameter("p_installationDate", installationDate);

        query.execute();
    }

    // Phương thức xóa đồng hồ nước
    public void deleteWaterMeter(Integer waterMeterId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_delete_water_meter");
        query.registerStoredProcedureParameter("p_waterMeterId", Integer.class, ParameterMode.IN);
        query.setParameter("p_waterMeterId", waterMeterId);
        query.execute();
    }
}
