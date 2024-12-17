package com.example.gilbutSpring.repository;

import com.example.gilbutSpring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository <대상 엔티티, 대표키의 타입>
public interface CommentRepository extends JpaRepository<Comment, Long> {
  // 특정 게시글의 모든 댓글 조회
  @Query (value =
          "SELECT * FROM comment WHERE article_id = :articleId",
          nativeQuery = true)
  List<Comment> findByArticleId(@Param("articleId") Long articleId);
  // 특정 닉네임의 모든 댓글 조회
  List<Comment> findByNickname(String nickname);
}
