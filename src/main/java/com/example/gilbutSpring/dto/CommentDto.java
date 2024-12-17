package com.example.gilbutSpring.dto;

import com.example.gilbutSpring.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
  private Long id;
  private Long articleId;
  private String nickname;
  private String body;

  public static CommentDto createCommentDto(Comment comment) {
    return new CommentDto(
            comment.getId(), // 댓글 엔티티 id
            comment.getArticle().getId(), // 댓글 엔티티가 속한 게시글의 id
            comment.getNickname(), // 댓글 엔티티의 nickname
            comment.getBody() // 댓글 엔티티의 body
    );
  }
}
