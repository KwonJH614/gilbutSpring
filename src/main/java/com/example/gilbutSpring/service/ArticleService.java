package com.example.gilbutSpring.service;

import com.example.gilbutSpring.dto.ArticleForm;
import com.example.gilbutSpring.entity.Article;
import com.example.gilbutSpring.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

  @Transactional
  public List<Article> createArticles(List<ArticleForm> dtos) {
    // 1. dto 묶음을 엔티티 묶음으로 변환하기
    List<Article> articleList = dtos.stream()
            .map(dto -> dto.toEntity())
            .collect(Collectors.toList());
    // 2. 엔티티 묶음을 DB에 저장하기
    articleList.stream()
            .forEach(article -> articleRepository.save(article));
    // 3. 강제 예외 발생시키기
    articleRepository.findById(-1L)
            .orElseThrow(() -> new IllegalArgumentException("결제 실패"));
    // 4. 결과 값 반환하기
    return articleList;
  }
}
