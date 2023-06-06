package com.example.blogAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blogAPI.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
