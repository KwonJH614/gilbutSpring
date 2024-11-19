package com.example.gilbutSpring.controller;

import com.example.gilbutSpring.dto.ArticleForm;
import com.example.gilbutSpring.entity.Article;
import com.example.gilbutSpring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {
  @Autowired
  private ArticleRepository articleRepository;

  @GetMapping("/articles/new")
  public String newArticleForm() {
    return "articles/new";
  }

  @PostMapping("/articles/create")
  public String createArticle(ArticleForm form) {
    log.info(form.toString());
    // System.out.println(form.toString());

    // DTO to entity
    Article article = form.toEntity();
    log.info(article.toString());
    // System.out.println(article.toString());

    // Save entity to DB via repository
    Article saved = articleRepository.save(article);
    log.info(saved.toString());
    // System.out.println(saved.toString());
    return "";
  }
}