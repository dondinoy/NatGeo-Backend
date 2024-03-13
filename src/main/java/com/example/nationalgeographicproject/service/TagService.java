package com.example.nationalgeographicproject.service;


import com.example.nationalgeographicproject.dto.CreateTagDto;
import com.example.nationalgeographicproject.dto.TagResponseDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface TagService {
    TagResponseDto addTag(long articleId, CreateTagDto dto);

    //add a post: get a request dto ->
    Collection<TagResponseDto> getAllTags();
    Collection<TagResponseDto> getAllTagsPagination(int pageNo, int pageSize);
    TagResponseDto getTagsById(long id);

    TagResponseDto updateTag(long id, CreateTagDto dto);

    TagResponseDto deleteTag(long id);
}
