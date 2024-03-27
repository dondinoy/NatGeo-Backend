package com.example.nationalgeographicproject.controller;


import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.dto.ArticleResponseDto;
import com.example.nationalgeographicproject.repository.ArticleRepository;
import com.example.nationalgeographicproject.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/articles")
public class ArticleController {
    //props:
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @CrossOrigin(origins = "http://localhost:5174")
    @GetMapping
    public ResponseEntity<Collection<ArticleResponseDto>> getAll(){
        var res = articleService.getAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("(page)")
    public ResponseEntity<Collection<ArticleResponseDto>> getPage(
            @RequestParam(defaultValue = "2", required = false) int pageSize,
            @RequestParam(defaultValue = "3", required = false) int pageNo,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir) {
        var res = articleService.getPage(pageNo,pageSize);
        return ResponseEntity.ok(res);
    }

    //add a post:
    @PostMapping("{categoryId}")
    public ResponseEntity<ArticleResponseDto> addArticle(
            @PathVariable long categoryId,
            @RequestBody @Valid CreateArticleDto dto,
            UriComponentsBuilder uriBuilder){
        //1) tell the service to save the post
        var saved = articleService.addArticle(categoryId,dto);
        //2) response uri:
        var uri = uriBuilder.path("/api/v1/article/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uri.toUri()).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity <ArticleResponseDto> getArticleById(
            @PathVariable Long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
        public ResponseEntity <ArticleResponseDto> updateArticleById(
                @PathVariable  long id,
                @RequestBody @Valid CreateArticleDto dto){

        System.out.println(dto);
        return ResponseEntity.ok(articleService.updateArticleById(id,dto));
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<ArticleResponseDto> deleteArticleById(@PathVariable long id){
        return ResponseEntity.ok(articleService.deleteArticleById(id));
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class PaginationException extends RuntimeException{
        public PaginationException(String message) {
            super(message);
        }
    }
}