package com.example.gilbutSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;
  @Column
  private String nickname;
  @Column
  private String body;

  @Override
  public String toString() {
    return "Comment{id=" + id + ", article=" + article.getId() + ", nickname='" + nickname + "', body='" + body + "'}";
  }
}
