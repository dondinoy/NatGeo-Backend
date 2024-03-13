package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.dto.CreateArticleDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ArticleService {


    ArticleResponseDto addArticle(long categoryId, CreateArticleDto dto);

    //add a post: get a request dto ->
    Collection<ArticleResponseDto> getAll();
    Collection<ArticleResponseDto> getPage(int pageNo, int pageSize);
    ArticleResponseDto getArticleById(long id);

    ArticleResponseDto updateArticleById(long id, CreateArticleDto dto);

    ArticleResponseDto deleteArticleById(long id);

    // save the entity
    // return a response dto
}