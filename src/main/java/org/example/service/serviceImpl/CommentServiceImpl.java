package org.example.service.serviceImpl;

import org.example.dao.CommentDao;
import org.example.dao.daoImpl.CommentDaoImpl;
import org.example.models.Comment;
import org.example.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

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
