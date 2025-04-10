	package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "News")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NewsID")
    Integer newsId;

    @Column(name = "Title", nullable = false, length = 100)
    String title;

    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    String content;

    @Column(name = "CreatedDate", updatable = false)
    LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "CreatedBy", foreignKey = @ForeignKey(name = "fk_news_employee"))
    Employee employee;

    @Column(name = "Status")
	Boolean status = true;

}
