// NewsService.java
package com.waterbilling.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.waterbilling.demo.dto.request.NewsRequest;
import com.waterbilling.demo.dto.response.NewsResponse;
import com.waterbilling.demo.exception.AppException;
import com.waterbilling.demo.exception.ErrorCode;
import com.waterbilling.demo.model.Account;
import com.waterbilling.demo.model.Employee;
import com.waterbilling.demo.repository.AccountRepository;
import com.waterbilling.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.model.News;
import com.waterbilling.demo.repository.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

//	public Page<NewsResponse> getAllNews(String search, Boolean status, Integer createdById, Pageable pageable) {
//		return newsRepository.findAllNews(search, status, createdById, pageable);
//	}


	public NewsResponse findNewsById(Integer id) {
		News news = newsRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_EXISTED));
		return NewsResponse.builder()
				.newsId(news.getNewsId())
				.content(news.getContent())
				.title(news.getTitle())
				.createdDate(news.getCreatedDate())
				.createdById(news.getEmployee().getEmployeeId())
				.build();
	}

	public NewsResponse createNews(NewsRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Account account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		Employee employee = employeeRepository.findByAccount(account)
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

		News news = News.builder()
				.content(request.getContent())
				.title(request.getTitle())
				.createdDate(LocalDateTime.now())
				.status(true)
				.employee(employee)
				.build();
		newsRepository.save(news);

		return NewsResponse.builder()
				.newsId(news.getNewsId())
				.content(news.getContent())
				.title(news.getTitle())
				.createdDate(news.getCreatedDate())
				.createdById(news.getEmployee().getEmployeeId())
				.build();
	}

	public NewsResponse updateNews(NewsRequest request, Integer id) {
		News news = newsRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_EXISTED));

		news.setContent(request.getContent());
		news.setTitle(request.getTitle());

		newsRepository.save(news);

		return NewsResponse.builder()
				.newsId(news.getNewsId())
				.content(news.getContent())
				.title(news.getTitle())
				.createdDate(news.getCreatedDate())
				.createdById(news.getEmployee().getEmployeeId())
				.build();

	}

	public void deleteNews(Integer id) {
		newsRepository.deleteById(id);
	}
}
