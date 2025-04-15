package com.waterbilling.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.waterbilling.demo.service.WaterMeterService;
import com.waterbilling.demo.model.WaterMeter;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/watermeters")
public class WaterMeterController {

    @Autowired
    private WaterMeterService waterMeterService;

    @PostMapping
    public ResponseEntity<String> addWaterMeter(@RequestBody Map<String, Object> req) {
        String serialNumber = (String) req.get("serialNumber");
        Integer facilityId = (Integer) req.get("facilityId");
        LocalDateTime installationDate = LocalDateTime.parse((String) req.get("installationDate"));
        waterMeterService.createWaterMeter(serialNumber, facilityId, installationDate);
        return ResponseEntity.ok("Thêm đồng hồ nước thành công");
    }

    // Đảm bảo rằng @GetMapping được khai báo đúng
    @GetMapping
    public ResponseEntity<List<WaterMeter>> getAllWaterMeters() {
        List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
        return ResponseEntity.ok(waterMeters);
    }

    @PutMapping("/{waterMeterId}")
    public ResponseEntity<String> updateWaterMeter(@PathVariable Integer waterMeterId, @RequestBody Map<String, Object> req) {
        String serialNumber = (String) req.get("serialNumber");
        Integer facilityId = (Integer) req.get("facilityId");
        LocalDateTime installationDate = LocalDateTime.parse((String) req.get("installationDate"));
        waterMeterService.updateWaterMeter(waterMeterId, serialNumber, facilityId, installationDate);
        return ResponseEntity.ok("Cập nhật đồng hồ nước thành công");
    }

    @DeleteMapping("/{waterMeterId}")
    public ResponseEntity<String> deleteWaterMeter(@PathVariable Integer waterMeterId) {
        waterMeterService.deleteWaterMeter(waterMeterId);
        return ResponseEntity.ok("Xóa đồng hồ nước thành công");
    }
}
