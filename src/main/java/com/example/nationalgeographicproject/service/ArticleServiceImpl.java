package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.entity.Article;
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
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ArticleResponseDto addArticle(long categoryId, CreateArticleDto dto) {
        System.out.println("addArticle. dto:"+dto);
        var entity = modelMapper.map(dto, Article.class);
        System.out.println("addArticle. article category:"+entity.getCategory());
        System.out.println("addArticle. article title:"+entity.getTitle());
        System.out.println("addArticle. article desc.:"+entity.getDescription());
        // Find the category by its ID
        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        // Set the category for the article
        entity.setCategory(category);

        // Save the article
        var saved = articleRepository.save(entity);

        // Add the article to the category's articles set
        category.getArticles().add(saved);

        // Update the category in the repository
        categoryRepository.save(category);

        return modelMapper.map(saved, ArticleResponseDto.class);
    }

    // Other methods remain unchanged


    @Override
    public Collection<ArticleResponseDto> getAll() {
        var all=articleRepository.findAll();
        return all.stream().map(m-> modelMapper.map(m, ArticleResponseDto.class)).toList();
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */

    @Override
    public Collection<ArticleResponseDto> getPage(int pageNo, int pageSize) {

        Pageable pageable= PageRequest.of(pageNo,pageSize);

        Page<Article> page= articleRepository.findAll(pageable);
        List<Article> articleList= page.getContent();
        return articleList.stream()
                .map(p-> modelMapper.map(p, ArticleResponseDto.class))
                .toList();
    }

    @Override
    public ArticleResponseDto getArticleById(long id) {
        Article article= getArticleEntityOrElseThrow(id);
        return modelMapper.map(article, ArticleResponseDto.class);
    }

    private Article getArticleEntityOrElseThrow(long id) {
        return articleRepository.findById(id)
                .orElseThrow(
                        ResourceNotFoundException
                                .newInstance("Post", "id", id)
                ); //()->new ResourceNotFoundException()
    }

    @Override
    public ArticleResponseDto updateArticleById(long id, CreateArticleDto dto) {
        Article article=getArticleEntityOrElseThrow(id);
        article.setContent(dto.getContent());
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setTags(article.getTags());
        article.setCategory(article.getCategory());
        var saved=articleRepository.save(article);
        return modelMapper.map(saved,ArticleResponseDto.class);
    }


    @Override
    public ArticleResponseDto deleteArticleById(long id) {
        Article article=getArticleEntityOrElseThrow(id);
        articleRepository.deleteById(id);
        return modelMapper.map(article,ArticleResponseDto.class);
    }

}
