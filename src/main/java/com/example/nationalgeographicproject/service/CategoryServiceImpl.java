package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.dto.CategoryResponseDto;
import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.dto.CreateCategoryDto;

import com.example.nationalgeographicproject.entity.Article;
import com.example.nationalgeographicproject.entity.Category;
import com.example.nationalgeographicproject.error.ResourceNotFoundException;
import com.example.nationalgeographicproject.repository.ArticleRepository;
import com.example.nationalgeographicproject.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
        private final CategoryRepository categoryRepository;
        private final ModelMapper modelMapper;
        private final ArticleRepository articleRepository;
    @Override
    public CategoryResponseDto addCategory(CreateCategoryDto dto) {
        var entity=modelMapper.map(dto, Category.class);
        var saved=categoryRepository.save(entity);
        System.out.println();
        return modelMapper.map(saved,CategoryResponseDto.class);
    }
    @Override
    public ArticleResponseDto addArticle(long categoryId, CreateArticleDto dto) {
        var entity = modelMapper.map(dto, Article.class);
        var category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + categoryId)
        );
        entity.setCategory(category); // Set the category for the article

        // Save the article
        var saved = articleRepository.save(entity);

        // Add the article to the category's articles set
        category.getArticles().add(saved);
        categoryRepository.save(category); // Persist the category changes

        return modelMapper.map(saved, ArticleResponseDto.class);
    }

    @Override
    public Collection<CategoryResponseDto> getAllCategories() {
        var allCategories=categoryRepository.findAll();
        return allCategories.stream().map(m->modelMapper.map(m,CategoryResponseDto.class)).toList();
    }

    @Override
    public Collection<CategoryResponseDto> getCategoriesPagination(int pageNo, int pageSize, String sortDir, String... sortBy) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Category> page= categoryRepository.findAll(pageable);
        List<Category> categoryList= page.getContent();
        return categoryList.stream()
                .map(p-> modelMapper.map(p, CategoryResponseDto.class))
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(long id) {
        Category category=getCategoryEntityOrElseThrow(id);
        return modelMapper.map(category,CategoryResponseDto.class);
    }

    private Category getCategoryEntityOrElseThrow(long id) {
        return categoryRepository.findById(id).orElseThrow(
                ResourceNotFoundException.newInstance("Category","id",id)
        );
    }

    @Override
    public CategoryResponseDto updateCategory(long id, CreateCategoryDto dto) {
        Category category=getCategoryEntityOrElseThrow(id);
        category.setName(dto.getName());
        category.setArticles(category.getArticles());
        var saved=categoryRepository.save(category);
        return modelMapper.map(saved,CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto deleteCategory(long id) {
        Category category=getCategoryEntityOrElseThrow(id);
        categoryRepository.deleteById(id);
        return modelMapper.map(category,CategoryResponseDto.class);
    }
}
