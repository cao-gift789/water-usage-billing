package com.waterbilling.demo.controller;

import com.waterbilling.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<List<Object[]>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @PostMapping
    public ResponseEntity<String> addNews(@RequestBody Map<String, Object> req) {
        String title = (String) req.get("title");
        String content = (String) req.get("content");
        int createdBy = (int) req.get("createdBy");

        newsService.addNews(title, content, createdBy);
        return ResponseEntity.ok("Thêm tin tức thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNews(@PathVariable int id, @RequestBody Map<String, Object> req) {
        String title = (String) req.get("title");
        String content = (String) req.get("content");

        newsService.updateNews(id, title, content);
        return ResponseEntity.ok("Cập nhật tin tức thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable int id) {
        newsService.deleteNews(id);
        return ResponseEntity.ok("Xóa tin tức thành công");
    }
}
