package com.example.gilbutSpring.service;

import com.example.gilbutSpring.dto.CommentDto;
import com.example.gilbutSpring.entity.Comment;
import com.example.gilbutSpring.repository.ArticleRepository;
import com.example.gilbutSpring.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private ArticleRepository articleRepository;


  public List<CommentDto> comments(Long articleId) {
    // 1. 댓글 조회
    List<Comment> comments = commentRepository.findByArticleId(articleId);
    // 2. 엔티티 -> DTO 변환
    List<CommentDto> dtos = new ArrayList<CommentDto>();
    for (int i = 0; i < comments.size(); i++) {
      Comment c = comments.get(i);
      CommentDto dto = CommentDto.createCommentDto(c);
      dtos.add(dto);
    }
    // 3. 결과 반환
    return dtos;
  }
}
