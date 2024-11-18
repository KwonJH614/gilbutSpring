package com.example.gilbutSpring.repository;

import com.example.gilbutSpring.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
