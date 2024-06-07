package com.example.nationalgeographicproject.dto;


import com.example.nationalgeographicproject.entity.Tag;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateArticleDto {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String content;
//
    private byte[] imageData;
}
