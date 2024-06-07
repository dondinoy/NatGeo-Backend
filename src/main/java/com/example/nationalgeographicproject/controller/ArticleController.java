package com.example.nationalgeographicproject.controller;

import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.entity.Article; // Correct import
import com.example.nationalgeographicproject.entity.Category; // Correct import
import com.example.nationalgeographicproject.service.ArticleService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/articles")
@CrossOrigin(origins = "http://localhost:5174")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<Collection<ArticleResponseDto>> getAll() {
        var res = articleService.getAllArticles();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/page")
    public ResponseEntity<Collection<ArticleResponseDto>> getPage(
            @RequestParam(defaultValue = "2", required = false) int pageSize,
            @RequestParam(defaultValue = "3", required = false) int pageNo,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir) {
        var res = articleService.getPage(pageNo, pageSize);
        return ResponseEntity.ok(res);
    }

    @PostMapping("{categoryId}")
    public ResponseEntity<ArticleResponseDto> addArticleToCategory(
            @PathVariable long categoryId,
            @RequestParam("imageData") MultipartFile imageFile,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("content") String content,
            UriComponentsBuilder uriBuilder) throws IOException {

        CreateArticleDto dto = CreateArticleDto.builder()
                .title(title)
                .description(description)
                .content(content)
                .build();

        var saved = articleService.addArticleToCategory(categoryId, dto, imageFile);
        var uri = uriBuilder.path("/api/v1/articles/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("content") String content,
            @RequestParam("category_id") Long categoryId,
            @RequestParam("imageData") MultipartFile imageFile) throws IOException {

        Article article = Article.builder()
                .title(title)
                .description(description)
                .content(content)
                .category(new Category(categoryId))
                .imageData(imageFile.getBytes()) // Save the binary data
                .build();

        Article savedArticle = articleService.saveArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long id) {
        ArticleResponseDto responseDto=articleService.getArticleById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticleById(
            @PathVariable long id,
            @RequestBody @Valid CreateArticleDto dto) {
        return ResponseEntity.ok(articleService.updateArticleById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> deleteArticleById(@PathVariable long id) {
        return ResponseEntity.ok(articleService.deleteArticleById(id));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class PaginationException extends RuntimeException {
        public PaginationException(String message) {
            super(message);
        }
    }
}