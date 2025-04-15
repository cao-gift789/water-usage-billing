package com.waterbilling.demo.controller;


import com.waterbilling.demo.dto.request.NewsRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.dto.response.NewsResponse;
import com.waterbilling.demo.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

//    @GetMapping
//    public ResponseEntity<Page<NewsResponse>> getAllNews(
//            @RequestParam(required = false) String search,
//            @RequestParam(required = false) Boolean status,
//            @RequestParam(required = false) Integer createdById,
//            @PageableDefault(size = 10, sort = "newsId") Pageable pageable) {
//        return ResponseEntity.ok(newsService.getAllNews(search, status, createdById, pageable));
//    }
    @GetMapping("/{id}")
    private ApiResponse<?> newsDetail(@PathVariable("id") Integer id) {
        return ApiResponse.<NewsResponse>builder()
                .result(newsService.findNewsById(id))
                .build();
    }

    @PostMapping
    private ApiResponse<?> create(@Valid @RequestBody NewsRequest request) {
        return ApiResponse.<NewsResponse>builder()
                .result(newsService.createNews(request))
                .build();
    }

    @PutMapping("/{id}")
    private ApiResponse<?> update(@Valid @RequestBody NewsRequest request, @PathVariable("id") Integer id) {
        return ApiResponse.<NewsResponse>builder()
                .result(newsService.updateNews(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable("id") Integer id) {
        newsService.deleteNews(id);
        return ApiResponse.<Void>builder().build();
    }

}
