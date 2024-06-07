package com.example.nationalgeographicproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @JsonIgnore
    private Set<Article> articles=new HashSet<>();

    public Category(Long id) {
        this.id = id;
    }

}