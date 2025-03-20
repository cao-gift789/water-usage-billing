package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "News")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NewsID")
    private Integer newsId;

    @Column(name = "Title", nullable = false, length = 100)
    private String title;

    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "CreatedDate", updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "CreatedBy", foreignKey = @ForeignKey(name = "fk_news_employee"))
    private Employee createdBy;

    @Column(name = "Status")
    private Boolean status = true;

    // Constructors
    public News() {
    }

    public News(String title, String content, Employee createdBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdDate = LocalDateTime.now();
    }

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
