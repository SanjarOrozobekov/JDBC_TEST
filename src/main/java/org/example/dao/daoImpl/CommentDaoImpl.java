package org.example.dao.daoImpl;

import org.example.dao.CommentDao;
import org.example.models.Comment;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public void createTable() {

    }

    @Override
    public void dropTable() {

    }

    @Override
    public void cleanTable() {

    }

    @Override
    public void saveComment(Comment comment) {

    }

    @Override
    public List<Comment> getComments() {
        return null;
    }

    @Override
    public Comment getCommentById(Long id) {
        return null;
    }

    @Override
    public void updateComment(Long id, Comment newComment) {

    }

    @Override
    public void deleteCommentById(Long id) {

    }

    @Override
    public List<Comment> findRecentCommentsByPost(Long postId, int limit) {
        return null;
    }

    @Override
    public Long countCommentsByPost(Long postId) {
        return 0l;
    }
}
