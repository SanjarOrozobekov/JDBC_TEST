package org.example.service.serviceImpl;

import org.example.dao.PostDao;
import org.example.dao.daoImpl.PostDaoImpl;
import org.example.models.Post;
import org.example.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    PostDao postDao = new PostDaoImpl();

    @Override
    public void crateTable() {
        postDao.crateTable();
    }

    @Override
    public void dropTable() {
        postDao.dropTable();
    }

    @Override
    public void cleanTable() {
        postDao.cleanTable();
    }

    @Override
    public void savePost(Post post) {
        postDao.savePost(post);
    }

    @Override
    public List<Post> getPosts() {
        return postDao.getPosts();
    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }

    @Override
    public void updatePost(Long id, Post newPost) {

    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public Long countPostsByUser(Long id) {
        return 0L;
    }
}
