package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.entity.Article; // Correct import
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface ArticleService {

    ArticleResponseDto addArticleToCategory(long categoryId, CreateArticleDto dto, MultipartFile imageFile) throws IOException;

    Article saveArticle(Article article); // Correct method signature
    ArticleResponseDto addArticle(CreateArticleDto dto, MultipartFile imageFile) throws IOException;

    Collection<ArticleResponseDto> getAllArticles();

    Collection<ArticleResponseDto> getPage(int pageNo, int pageSize);

    ArticleResponseDto getArticleById(long id);

    ArticleResponseDto updateArticleById(long id, CreateArticleDto dto);

    ArticleResponseDto deleteArticleById(long id);
}