package com.waterbilling.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsResponse {

    private Integer newsId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private Integer createdById;
    private Boolean status;

}
