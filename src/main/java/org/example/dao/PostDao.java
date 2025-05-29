package org.example.dao;

import org.example.models.Post;

import java.util.List;

public interface PostDao {
    //todo ddl
    void crateTable();

    void dropTable();

    void cleanTable();

    //todo dml

    void savePost(Post post);

    List<Post> getPosts();

    Post getPostById(Long id);

    void updatePost(Long id,Post newPost);

    void deletePost(Long id);

    Long countPostsByUser(Long id);
}
