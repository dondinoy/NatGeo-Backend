package com.example.nationalgeographicproject.service;

import com.example.nationalgeographicproject.dto.CreateArticleDto;
import com.example.nationalgeographicproject.dto.DeleteArticleResponseDto;
import com.example.nationalgeographicproject.dto.ResponseArticleDto;
import com.example.nationalgeographicproject.entity.Article;
import com.example.nationalgeographicproject.error.ResourceNotFoundException;
import com.example.nationalgeographicproject.repository.ArticleRepository;
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

    @Override
    public ResponseArticleDto addArticle(CreateArticleDto dto) {
        //DTO -> Entity
        var entity=modelMapper.map(dto, Article.class);
        //Entity ->Save
        var saved=articleRepository.save(entity);
        //Return entity->response dto
        return modelMapper.map(saved,ResponseArticleDto.class);
    }

    @Override
    public Collection<ResponseArticleDto> getAll() {
        var all=articleRepository.findAll();
        return all.stream().map(m-> modelMapper.map(m, ResponseArticleDto.class)).toList();
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */

    @Override
    public Collection<ResponseArticleDto> getPage(int pageNo, int pageSize) {

        Pageable pageable= PageRequest.of(pageNo,pageSize);

        Page<Article> page= articleRepository.findAll(pageable);
        List<Article> articleList= page.getContent();
        return articleList.stream()
                .map(p-> modelMapper.map(p, ResponseArticleDto.class))
                .toList();
    }

    @Override
    public ResponseArticleDto getArticleById(long id) {
        return null;
    }

    @Override
    public ResponseArticleDto updateArticleById(long id, CreateArticleDto dto) {
        return null;
    }


    private Article getArticleEntityByIdOrElseThrow(long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("article", "id", id)
        );
    }

    @Override
    public DeleteArticleResponseDto deleteArticleById(long id) {
        //check for existence before deleting:
        var article =  articleRepository.findById(id);
        //delete:
        articleRepository.deleteById(id);
        //return true if existed before deletion:
        return DeleteArticleResponseDto.builder()
                .deleted(article.isPresent())
                .article(modelMapper.map(article, ResponseArticleDto.class))
                .build();
    }
}
