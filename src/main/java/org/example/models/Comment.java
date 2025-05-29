package org.example.models;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private Long id;

    private String text;

    private LocalDate commentDate;

    private Long postId;

    private Long userId;

    public Comment(String text, LocalDate commentDate, Long postId, Long userId) {
        this.text = text;
        this.commentDate = commentDate;
        this.postId = postId;
        this.userId = userId;
    }
}
