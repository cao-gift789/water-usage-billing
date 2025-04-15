package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.dto.response.JoinRequestOfUserResponse;
import com.waterbilling.demo.service.JoinRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/join-requests")
public class JoinRequestController {

    @Autowired
    private JoinRequestService joinRequestService;

    @GetMapping
    public ApiResponse<?> getListJoinRequestOfUser(){
        return ApiResponse.<List<JoinRequestOfUserResponse>>builder()
                .result(joinRequestService.getJoinRequestOfUser())
                .build();
    }

    @DeleteMapping("/confirm/{requestId}")
    public ApiResponse<?> confirm(@PathVariable("requestId") Integer requestId){
        joinRequestService.confirm(requestId);
        return ApiResponse.<String>builder()
                .result("Xác nhận cho phép tham gia")
                .build();
    }

    @PostMapping("/{facilityId}")
    public ApiResponse<?> create(@PathVariable("facilityId") Integer facilityId){
        joinRequestService.create(facilityId);
        return ApiResponse.<String>builder()
                .result("Tạo request thành công")
                .build();
    }
    @DeleteMapping("/refuse/{requestId}")
    public ApiResponse<?> refuse(@PathVariable("requestId") Integer requestId){
        joinRequestService.refuse(requestId);
        return ApiResponse.<String>builder()
                .result("Hủy yêu cầu thành công.")
                .build();
    }
    @DeleteMapping("/cancel/{requestId}")
    public ApiResponse<?> cancel(@PathVariable("requestId") Integer requestId){
        joinRequestService.cancel(requestId);
        return ApiResponse.<String>builder()
                .result("Hủy yêu cầu thành công.")
                .build();
    }
}
