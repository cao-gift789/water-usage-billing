package com.waterbilling.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterbilling.demo.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
}
