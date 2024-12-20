package com.example.gilbutSpring.controller;

import com.example.gilbutSpring.dto.ArticleForm;
import com.example.gilbutSpring.entity.Article;
import com.example.gilbutSpring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    return "redirect:/articles/" + saved.getId();
  }

  @GetMapping("/articles/{id}")
  public String show(@PathVariable Long id, Model model) {
    log.info("id = " + id);
    // 1. Fetch data by id
    Article article = articleRepository.findById(id).orElse(null);
    // 2. Add data to the model
    model.addAttribute("article", article);
    // 3. Return the view page
    return "articles/show";
  }

  @GetMapping("/articles")
  public String index(Model model) {
    // 1. Fetch all data
    List<Article> articleList = articleRepository.findAll();
    // 2. Add data to the model
    model.addAttribute("articleList", articleList);
    // 3. Set the view page
    return "articles/index";
  }

  @GetMapping("/articles/{id}/edit")
  public String edit(@PathVariable Long id, Model model) {
    // Fetch data to update
    Article article = articleRepository.findById(id).orElse(null);
    // Add data to the model
    model.addAttribute("article", article);
    // Set the view page
    return "articles/edit";
  }

  @PostMapping("/articles/update")
  public String update(ArticleForm form) {
    log.info(form.toString());
    // 1. Convert DTO to Entity
    Article article = form.toEntity();
    log.info(article.toString());
    // 2. Save Entity to DB
    // 2-1. Fetch existing data from DB
    Article target = articleRepository.findById(article.getId()).orElse(null);
    // 2-2 Update existing data values
    if (target != null) { articleRepository.save(article); }
    // 3. Redirect to the edit result page
    return "redirect:/articles/" + article.getId();
  }

  @GetMapping("/articles/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes rttr) {
    log.info("삭제 요청");
    // 1. 삭제 대상 사져오기
    Article target = articleRepository.findById(id).orElse(null);
    log.info(target.toString());
    // 2. 대상 엔티티 삭제
    if (target != null) {
      articleRepository.delete(target);
      rttr.addFlashAttribute("msg", "삭제됐습니다");
    }
    // 3. 결과 페이지로 리다이렉트
    return "redirect:/articles";
  }
}
