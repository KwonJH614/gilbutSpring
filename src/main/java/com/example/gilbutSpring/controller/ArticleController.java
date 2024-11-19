package com.example.gilbutSpring.controller;

import com.example.gilbutSpring.dto.ArticleForm;
import com.example.gilbutSpring.entity.Article;
import com.example.gilbutSpring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.asm.IModelFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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

  @GetMapping("/articles/{id}")
  public String show(@PathVariable Long id, Model model) {
    log.info("id = " + id);
    // 1. Fetch data by id
    Article articleEntity = articleRepository.findById(id).orElse(null);
    // 2. Add data to the model
    model.addAttribute("article", articleEntity);
    // 3. Return the view page
    return "articles/show";
  }

  @GetMapping("/articles")
  public String index(Model model) {
    // 1. Fetch all data
    List<Article> articleEntityList = articleRepository.findAll();
    // 2. Add data to the model
    model.addAttribute("articleList", articleEntityList);
    // 3. Set the view page
    return "articles/index";
  }
}
