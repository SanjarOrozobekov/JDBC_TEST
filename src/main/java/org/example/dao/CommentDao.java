package org.example.dao;

import org.example.models.Comment;

import java.util.List;

public interface CommentDao {
//todo ddl

    void createTable();

    void dropTable();

    void cleanTable();


    //todo dml

    void saveComment(Comment comment);

    List<Comment> getComments();

    Comment getCommentById(Long id);

    void updateComment(Long id,Comment newComment);

    void deleteCommentById(Long id);

    List<Comment> findRecentCommentsByPost(Long postId,int limit);

    Long countCommentsByPost(Long postId);
}
