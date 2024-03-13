package com.example.nationalgeographicproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTagDto {
    @NotNull
    @Size(min = 2, max = 128, message = "name must be between 2-128 chars")
    private String name;
}
