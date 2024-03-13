package com.example.nationalgeographicproject.repository;

import com.example.nationalgeographicproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
