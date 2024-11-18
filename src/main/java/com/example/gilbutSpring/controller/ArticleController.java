package com.example.gilbutSpring.controller;

import com.example.gilbutSpring.dto.ArticleForm;
import com.example.gilbutSpring.entity.Article;
import com.example.gilbutSpring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
  @Autowired
  private ArticleRepository articleRepository;

  @GetMapping("/articles/new")
  public String newArticleForm() {
    return "articles/new";
  }

  @PostMapping("/articles/create")
  public String createArticle(ArticleForm form) {
    System.out.println(form.toString());
    // DTO to entity
    Article article = form.toEntity();
    System.out.println(article.toString());
    // Save entity to DB via repository
    Article saved = articleRepository.save(article);
    System.out.println(saved.toString());
    return "";
  }
}
