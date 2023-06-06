package com.example.blogAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blogAPI.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	Tag findByName(String name);
}
