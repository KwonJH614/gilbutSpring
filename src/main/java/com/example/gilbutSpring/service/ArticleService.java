package com.example.gilbutSpring.service;

import com.example.gilbutSpring.dto.ArticleForm;
import com.example.gilbutSpring.entity.Article;
import com.example.gilbutSpring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
  @Autowired
  private ArticleRepository articleRepository; // 게시글 리포지토리 객체 주입


  public List<Article> index() {
    return articleRepository.findAll();
  }

  public Article show(Long id) {
    return articleRepository.findById(id).orElse(null);
  }

  public Article create(ArticleForm dto) {
    Article article = dto.toEntity();
    if (article.getId() != null) {
      return null;
    }
    return articleRepository.save(article);
  }

  public Article update(Long id, ArticleForm dto) {
  }
}
