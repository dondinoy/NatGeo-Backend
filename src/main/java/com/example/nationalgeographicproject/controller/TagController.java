package com.example.nationalgeographicproject.controller;

import com.example.nationalgeographicproject.dto.CreateTagDto;
import com.example.nationalgeographicproject.dto.TagResponseDto;
import com.example.nationalgeographicproject.repository.TagRepository;
import com.example.nationalgeographicproject.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tags")
public class TagController {
    private final TagService tagService;
    private final TagRepository tagRepository;

    @GetMapping
    public ResponseEntity<Collection<TagResponseDto>>getAllTags(){
        var res=tagService.getAllTags();
        return ResponseEntity.ok(res);
    }
    @PostMapping("{articleId}")
    public ResponseEntity<TagResponseDto>addTag(
            @PathVariable long articleId,
            @RequestBody @Valid
            CreateTagDto dto,
            UriComponentsBuilder uriBuilder
            ){
        var saved=tagService.addTag(articleId, dto);
        var uri= uriBuilder.path("/api/v1/tags/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uri.toUri()).body(saved);
    }
    @GetMapping("/{id}")
    public ResponseEntity <TagResponseDto>getTagsById(
            @PathVariable long id,
            @RequestBody @Valid CreateTagDto dto){
        System.out.println(dto);
        return ResponseEntity.ok(tagService.getTagsById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity <TagResponseDto> updateTag(
            @PathVariable long id,
            @RequestBody @Valid CreateTagDto dto){
        System.out.println(dto);
        return ResponseEntity.ok(tagService.updateTag(id,dto));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<TagResponseDto>deleteTag(@PathVariable long id){
        return ResponseEntity.ok(tagService.deleteTag(id));
    }

}
