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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ArticleResponseDto addArticleToCategory(long categoryId, CreateArticleDto dto, MultipartFile imageFile) throws IOException {
        var entity = modelMapper.map(dto, Article.class);
        if (imageFile != null && !imageFile.isEmpty()) {
            entity.setImageData(imageFile.getBytes());
        }

        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));

        entity.setCategory(category);

        var saved = articleRepository.save(entity);
        category.getArticles().add(saved);
        categoryRepository.save(category);
        ArticleResponseDto response=modelMapper.map(saved,ArticleResponseDto.class);
        response.setImageData(Base64.getEncoder().encodeToString(saved.getImageData()));
        return response;
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public ArticleResponseDto addArticle(CreateArticleDto dto, MultipartFile imageFile) throws IOException {
        var entity = modelMapper.map(dto, Article.class);
        if (imageFile != null && !imageFile.isEmpty()) {
            entity.setImageData(imageFile.getBytes());
        }

        var saved = articleRepository.save(entity);
        return modelMapper.map(saved, ArticleResponseDto.class);
    }

    public List<ArticleResponseDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(article -> {
            ArticleResponseDto dto = modelMapper.map(article, ArticleResponseDto.class);
            String base64Image = (article.getImageData() != null)
                    ? Base64.getEncoder().encodeToString(article.getImageData())
                    : null;
            dto.setImageData(base64Image);
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public Collection<ArticleResponseDto> getPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Article> page = articleRepository.findAll(pageable);
        List<Article> articleList = page.getContent();
        return articleList.stream()
                .map(p -> modelMapper.map(p, ArticleResponseDto.class))
                .toList();
    }

    @Override
    public ArticleResponseDto getArticleById(long id) {
        Article article=getArticleEntityOrElseThrow(id);
        ArticleResponseDto responseDto=modelMapper.map(article,ArticleResponseDto.class);


        String base64Image= (article.getImageData() !=null)
                ?Base64.getEncoder().encodeToString(article.getImageData()):null;
        assert base64Image != null;
        responseDto.setImageData(base64Image);

     return responseDto;

    }



    private Article getArticleEntityOrElseThrow(long id) {
        return articleRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Article", "id", id)
                );
    }

    @Override
    public ArticleResponseDto updateArticleById(long id, CreateArticleDto dto) {
        Article article = getArticleEntityOrElseThrow(id);
        article.setContent(dto.getContent());
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setCategory(article.getCategory());
        var saved = articleRepository.save(article);
        return modelMapper.map(saved, ArticleResponseDto.class);
    }

    @Override
    public ArticleResponseDto deleteArticleById(long id) {
        Article article = getArticleEntityOrElseThrow(id);
        articleRepository.deleteById(id);
        return modelMapper.map(article, ArticleResponseDto.class);
    }
}