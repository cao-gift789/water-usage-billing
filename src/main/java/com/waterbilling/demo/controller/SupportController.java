package com.waterbilling.demo.controller;

import com.waterbilling.demo.dto.request.SupportRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.service.SupportService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supports")
public class SupportController {

    @Autowired
    private SupportService supportService;


    @PostMapping
    public ApiResponse<?> support(@Valid @RequestBody SupportRequest request) {
        return ApiResponse.<Support>builder()
                .result(supportService.saveSupport(request))
                .build();

    }

    @GetMapping
    public ApiResponse<?> getAllSupport() {
        return ApiResponse.<List<Support>>builder()
                .result(supportService.findALl())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getSupport(@PathVariable("id") Integer id) {
        return ApiResponse.<Support>builder()
                .result(supportService.getSupport(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable("id") Integer id) {
        supportService.deleteSupport(id);
        return ApiResponse.<String>builder()
                .build();
    }
}
