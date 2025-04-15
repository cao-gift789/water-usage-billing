package com.waterbilling.demo.service;

import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.Facility;
import com.waterbilling.demo.model.LocationManager;
import com.waterbilling.demo.model.User;
import com.waterbilling.demo.repository.FacilityRepository;
import com.waterbilling.demo.repository.LocationManagerRepository;
import com.waterbilling.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LocationManagerService {

    @Autowired
    private LocationManagerRepository locationManagerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    public void deleteMember(Integer facilityId, Integer memberId) {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_NOT_EXIST));

        if(!Objects.equals(user.getUserId(), facility.getUser().getUserId())
                || Objects.equals(user.getUserId(), memberId))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        User member = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        LocationManager locationManager = locationManagerRepository.findAllByUserAndFacility(member, facility)
                .orElseThrow(() -> new IllegalArgumentException("Hộ không có thành viên này"));

        locationManagerRepository.delete(locationManager);
    }

    public void leave(Integer facilityId) {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_NOT_EXIST));

        if(Objects.equals(user.getUserId(), facility.getUser().getUserId()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        LocationManager locationManager = locationManagerRepository.findAllByUserAndFacility(user, facility)
                .orElseThrow(() -> new IllegalArgumentException("Bạn ko phải là thành viên của hộ"));

        locationManagerRepository.delete(locationManager);
    }
}
