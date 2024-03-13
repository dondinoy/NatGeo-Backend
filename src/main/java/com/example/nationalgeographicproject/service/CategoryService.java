package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.dto.CategoryResponseDto;
import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.dto.CreateCategoryDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CategoryService {
    CategoryResponseDto addCategory(CreateCategoryDto dto);


    ArticleResponseDto addArticle(long categoryId, CreateArticleDto dto);

    //add a post: get a request dto ->
    Collection<CategoryResponseDto> getAllCategories();
    Collection<CategoryResponseDto> getCategoriesPagination(int pageNo, int pageSize, String sortDir, String... sortBy);
    CategoryResponseDto getCategoryById(long id);
    
    CategoryResponseDto updateCategory(long id, CreateCategoryDto dto);

    CategoryResponseDto deleteCategory(long id);

}
