package com.example.nationalgeographicproject.entity;

import com.example.nationalgeographicproject.dto.CreateArticleDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints ={@UniqueConstraint(columnNames = "title")})
public class Article {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String category;
    @NotNull
    private String description;
    @NotNull
    private String content;

    public Article(Long id, CreateArticleDto dto){
        this.id=id;
        this.title=dto.getTitle();
        this.description=dto.getDescription();
        this.content=dto.getContent();
    }
}
