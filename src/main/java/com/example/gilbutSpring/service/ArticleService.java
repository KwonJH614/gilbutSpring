package com.example.gilbutSpring.service;

import com.example.gilbutSpring.dto.ArticleForm;
import com.example.gilbutSpring.entity.Article;
import com.example.gilbutSpring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
    return null;
  }

  public Article update(Long id, ArticleForm dto) {
    // 1. DTO -> 엔티티 변환
    Article article = dto.toEntity();
    log.info("id : {}, article : {}", id, article.toString());
    // 2. 타깃 조회
    Article target = articleRepository.findById(id).orElse(null);
    // 3. 잘못된 요쳥 처리
    if (target == null || id != article.getId()) {
      // 400, 잘못된 요청 응답
      log.info("잘못된 요청 : id : {}, article : {}", id, article.toString());
      return null;
    }
    // 4. 업데이트 및 정상응답(200)
    target.patch(article);
    Article updated = articleRepository.save(target);
    return updated;
  }

  public Article delete(Long id) {
    // 1. 대상 찾기
    Article target = articleRepository.findById(id).orElse(null);
    // 2. 잘못된 요청 처리
    if (target == null) {
      return null;
    }
    // 3. 대상 삭제
    articleRepository.delete(target);
    return target;
  }
}
