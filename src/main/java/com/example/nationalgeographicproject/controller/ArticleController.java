package com.example.nationalgeographicproject.controller;


import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.dto.DeleteArticleResponseDto;
import com.example.nationalgeographicproject.dto.ResponseArticleDto;
import com.example.nationalgeographicproject.repository.ArticleRepository;
import com.example.nationalgeographicproject.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    //props:
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @GetMapping
    public ResponseEntity<Collection<ResponseArticleDto>> getAll(){
        var res = articleService.getAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/page")
    public ResponseEntity<Collection<ResponseArticleDto>> getPage(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir) {
        var res = articleService.getPage(pageNo,pageSize);
        return ResponseEntity.ok(res);
    }

    //add a post:
    @PostMapping
    public ResponseEntity<ResponseArticleDto> addArticle(
            @RequestBody @Valid CreateArticleDto dto,
            UriComponentsBuilder uriBuilder){
        //1) tell the service to save the post
        var saved = articleService.addArticle(dto);
        //2) response uri:
        var uri = uriBuilder.path("/api/v1/article/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uri.toUri()).body(saved);
    }
    @GetMapping("/{id}")
    public ResponseEntity <ResponseArticleDto> getArticleById(
            @PathVariable @Valid @NotNull Long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }
    @PutMapping("/{id}")
        public ResponseEntity <ResponseArticleDto> updateArticleById(
                @PathVariable @Valid @NotNull Long id,
                @RequestBody @Valid CreateArticleDto dto){
        return ResponseEntity.ok(articleService.updateArticleById(id,dto));
    }

    @DeleteMapping("/(id)")
        public ResponseEntity<DeleteArticleResponseDto> deleteArticleById(@PathVariable @Valid @NotNull Long id){
        return ResponseEntity.ok(articleService.deleteArticleById(id));
    }
}