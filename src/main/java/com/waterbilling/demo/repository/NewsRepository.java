package com.waterbilling.demo.repository;

import com.waterbilling.demo.dto.response.NewsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.waterbilling.demo.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {

//    @Query("SELECT new com.waterbilling.demo.dto.response.NewsResponse(n.newsId, n.title, n.content, n.createdDate, n.createdBy.employeeId, n.status) " +
//            "FROM News n " +
//            "WHERE (:search IS NULL OR LOWER(n.title) LIKE LOWER(CONCAT('%', :search, '%')) " +
//            "OR LOWER(n.content) LIKE LOWER(CONCAT('%', :search, '%'))) " +
//            "AND (:status IS NULL OR n.status = :status) " +
//            "AND (:createdById IS NULL OR n.createdBy.employeeId = :createdById)")
//    Page<NewsResponse> findAllNews(@Param("search") String search,
//                                     @Param("status") Boolean status,
//                                     @Param("createdById") Integer createdById,
//                                     Pageable pageable);
}
