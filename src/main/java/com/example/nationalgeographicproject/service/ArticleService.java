package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.dto.DeleteArticleResponseDto;
import com.example.nationalgeographicproject.dto.ResponseArticleDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ArticleService {
    ResponseArticleDto addArticle(CreateArticleDto dto);

    //add a post: get a request dto ->
    Collection<ResponseArticleDto> getAll();
    Collection<ResponseArticleDto> getPage(int pageNo, int pageSize);
    ResponseArticleDto getArticleById(long id);

    ResponseArticleDto updateArticleById(long id, CreateArticleDto dto);

    DeleteArticleResponseDto deleteArticleById(long id);

    // save the entity
    // return a response dto
}