package com.example.nationalgeographicproject.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteArticleResponseDto {
    private boolean deleted;
    private ResponseArticleDto article;
}
