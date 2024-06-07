package com.example.nationalgeographicproject.dto;

import com.example.nationalgeographicproject.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponseDto {
    private int pageNo;
    private int pageSize;

    private Long id;
    private String title;
    private String description;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Collection<Tag>tags;
    private String imageData;
//    private String imageName;

}
