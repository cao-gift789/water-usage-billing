// NewsService.java
package com.waterbilling.demo.service;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> getAllNews() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_get_all_news");
        return query.getResultList();
    }

    public void addNews(String title, String content, int createdBy) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_add_news");

        query.registerStoredProcedureParameter("p_title", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_content", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_createdBy", Integer.class, ParameterMode.IN);

        query.setParameter("p_title", title);
        query.setParameter("p_content", content);
        query.setParameter("p_createdBy", createdBy);

        query.execute();
    }

    public void updateNews(int newsId, String title, String content) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_update_news");

        query.registerStoredProcedureParameter("p_newsId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_title", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_content", String.class, ParameterMode.IN);

        query.setParameter("p_newsId", newsId);
        query.setParameter("p_title", title);
        query.setParameter("p_content", content);

        query.execute();
    }

    public void deleteNews(int newsId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_delete_news");

        query.registerStoredProcedureParameter("p_newsId", Integer.class, ParameterMode.IN);
        query.setParameter("p_newsId", newsId);

        query.execute();
    }
}
