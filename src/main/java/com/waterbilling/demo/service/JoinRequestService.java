package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.response.JoinRequestOfUserResponse;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.*;
import com.waterbilling.demo.repository.FacilityRepository;
import com.waterbilling.demo.repository.JoinRequestRepository;
import com.waterbilling.demo.repository.LocationManagerRepository;
import com.waterbilling.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JoinRequestService {

    @Autowired
    private JoinRequestRepository joinRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private LocationManagerRepository locationManagerRepository;

    public List<JoinRequestOfUserResponse> getJoinRequestOfUser(){
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        return joinRequestRepository.findAllByUser(user)
                .stream()
                .map(jR -> JoinRequestOfUserResponse.builder()
                        .requestDate(jR.getRequestDate())
                        .requestId(jR.getRequestId())
                        .facilityId(jR.getFacility().getFacilityId())
                        .build())
                .toList();

    }
    public void create(Integer facilityId) {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new AppException(ErrorCode.FACILITY_NOT_EXIST));

        locationManagerRepository.findAllByUserAndFacility(user, facility)
                .orElseThrow(() -> new IllegalArgumentException("User đã tham gia hộ này rồi"));
        joinRequestRepository.save(
                JoinRequest.builder()
                        .facility(facility)
                        .user(user)
                        .requestDate(LocalDateTime.now())
                        .build()
        );
    }

    @Transactional
    public void confirm(Integer requestId) {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        JoinRequest joinRequest = joinRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_EXIST));

        if(!Objects.equals(user.getUserId(), joinRequest.getFacility().getUser().getUserId()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        Facility facility = joinRequest.getFacility();
        locationManagerRepository.save(LocationManager.builder()
                        .grantedDate(LocalDateTime.now())
                        .facility(facility)
                        .user(user)
                        .build());
        joinRequestRepository.delete(joinRequest);
    }
    public void refuse(Integer requestId){
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        JoinRequest joinRequest = joinRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_EXIST));

        if(!Objects.equals(user.getUserId(), joinRequest.getFacility().getUser().getUserId()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        joinRequestRepository.delete(joinRequest);

    }
    public void cancel(Integer requestId) {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByAccountId(Integer.parseInt(accountId))
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        JoinRequest joinRequest = joinRequestRepository.findById(requestId)
                .orElseThrow(() -> new AppException(ErrorCode.REQUEST_NOT_EXIST));
        if(!Objects.equals(user.getUserId(), joinRequest.getUser().getUserId()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        joinRequestRepository.delete(joinRequest);
    }
}
