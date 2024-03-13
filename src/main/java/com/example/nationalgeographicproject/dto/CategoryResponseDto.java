package com.example.nationalgeographicproject.dto;

import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.entity.Article;
import com.example.nationalgeographicproject.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private int pageNo;
    private int pageSize;

    private Long id;
    private String name;

    private Collection<Article>articles;

}
