package com.example.nationalgeographicproject.controller;

import com.example.nationalgeographicproject.dto.CategoryResponseDto;
import com.example.nationalgeographicproject.dto.CreateCategoryDto;
import com.example.nationalgeographicproject.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<Collection<CategoryResponseDto>> getAllCategories(){
        var res = categoryService.getAllCategories();
        return ResponseEntity.ok(res);
    }


    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(
            @RequestBody @Valid CreateCategoryDto dto,
            UriComponentsBuilder uriBuilder){
        //1) tell the service to save the post
        var saved = categoryService.addCategory(dto);
        //2) response uri:
        var uri = uriBuilder.path("/api/v1/article/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uri.toUri()).body(saved);
    }
    @GetMapping("(page)")
    public ResponseEntity<Collection<CategoryResponseDto>> getCategoriesPagination(
            @RequestParam(defaultValue = "2", required = false) int pageSize,
            @RequestParam(defaultValue = "3", required = false) int pageNo,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir) {
        var res = categoryService.getCategoriesPagination(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/(id)")
    public ResponseEntity <CategoryResponseDto> getCategoryById(
            @PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable  long id,
            @RequestBody @Valid CreateCategoryDto dto
    ){
        System.out.println(dto);
        return ResponseEntity.ok(categoryService.updateCategory(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategoryById(@PathVariable long id){
        return ResponseEntity.ok( categoryService.deleteCategory(id));
    }
}
