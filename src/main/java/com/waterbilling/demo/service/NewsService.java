package com.waterbilling.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterbilling.demo.model.News;
import com.waterbilling.demo.model.Support;
import com.waterbilling.demo.repository.NewsRepository;
import com.waterbilling.demo.repository.SupportRepository;

@Service
public class NewsService {
	@Autowired
	private NewsRepository newsRepository;
	
	public List<News> findNews () {
		 List<News> resualList=newsRepository.findAll();
		 return resualList;
	}
}
