package com.example.nationalgeographicproject.repository;

import com.example.nationalgeographicproject.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}