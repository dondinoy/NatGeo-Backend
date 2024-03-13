package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.CreateTagDto;
import com.example.nationalgeographicproject.dto.TagResponseDto;
import com.example.nationalgeographicproject.entity.Article;
import com.example.nationalgeographicproject.entity.Category;
import com.example.nationalgeographicproject.entity.Tag;
import com.example.nationalgeographicproject.error.ResourceNotFoundException;
import com.example.nationalgeographicproject.repository.ArticleRepository;
import com.example.nationalgeographicproject.repository.CategoryRepository;
import com.example.nationalgeographicproject.repository.TagRepository;
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
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Override
    public TagResponseDto addTag(long articleId, CreateTagDto dto) {
        var entity=modelMapper.map(dto, Tag.class);
        var article = articleRepository.findById(articleId).orElseThrow(
                () ->
                     new EntityNotFoundException("No article with id " + articleId));
        entity.getArticles().add(article);
        var saved=tagRepository.save(entity);
        article.getTags().add(saved);
        articleRepository.save(article);
        return modelMapper.map(saved,TagResponseDto.class);
    }

    @Override
    public Collection<TagResponseDto> getAllTags() {
        var allTags=tagRepository.findAll();
        return allTags.stream().map(m->modelMapper.map(m,TagResponseDto.class)).toList();
    }

    @Override
    public Collection<TagResponseDto> getAllTagsPagination(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Tag> page= tagRepository.findAll(pageable);
        List<Tag> tagList= page.getContent();
        return tagList.stream()
                .map(p-> modelMapper.map(p, TagResponseDto.class))
                .toList();    }


    @Override
    public TagResponseDto getTagsById(long id) {
        Tag tag=getTagEntityOrElseThrow(id);
        return modelMapper.map(tag,TagResponseDto.class);
    }

    private Tag getTagEntityOrElseThrow(long id) {
        return tagRepository.findById(id)
                .orElseThrow(ResourceNotFoundException.newInstance("Tag","id",id));
    }

    @Override
    public TagResponseDto updateTag(long id, CreateTagDto dto) {
        Tag tag=getTagEntityOrElseThrow(id);
        tag.setName(dto.getName());
        tag.setArticles(tag.getArticles());
        var saved=tagRepository.save(tag);
        return modelMapper.map(saved,TagResponseDto.class);
    }

    @Override
    public TagResponseDto deleteTag(long id) {
        Tag tag=getTagEntityOrElseThrow(id);
        tagRepository.deleteById(id);
        return modelMapper.map(tag,TagResponseDto.class);
    }
}
