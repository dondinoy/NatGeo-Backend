package com.example.nationalgeographicproject.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateArticleDto {
    @NotNull
    @Size(min = 4, max = 300)
    private String title;
    @NotNull
    @Size(message = "no less than 4", max = 5000)
    private String description;
    @NotNull
    @Size(min = 4)
    private String content;
}
