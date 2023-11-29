package com.example.nationalgeographicproject.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseArticleDto {
    private Long id;
    private String title;
    private String description;
    private String content;
}
