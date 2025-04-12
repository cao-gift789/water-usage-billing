package com.waterbilling.demo.controller;


import com.waterbilling.demo.dto.request.NewsRequest;
import com.waterbilling.demo.dto.response.ApiResponse;
import com.waterbilling.demo.dto.response.NewsResponse;
import com.waterbilling.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    private ApiResponse<?> newsDetail(@PathVariable("id") Integer id) {
        return ApiResponse.<NewsResponse>builder()
                .result(newsService.findNewsById(id))
                .build();
    }

    @PostMapping
    private ApiResponse<?> create(@RequestBody NewsRequest request) {
        return ApiResponse.<NewsResponse>builder()
                .result(newsService.createNews(request))
                .build();
    }

    @PutMapping("/{id}")
    private ApiResponse<?> update(@RequestBody NewsRequest request, @PathVariable("id") Integer id) {
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
