package org.example.dao.daoImpl;

import org.example.config.JdbcConfig;
import org.example.dao.PostDao;
import org.example.models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {
    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public void crateTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS posts (
                id SERIAL PRIMARY KEY ,
                image VARCHAR(255) NOT NULL ,
                description VARCHAR(255) NOT NULL ,
                created_date date NOT NULL ,
                user_id INT REFERENCES users(id))
                """;
        try(Statement statement = connection.createStatement()){
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql ="DROP TABLE IF EXISTS posts";
    }

    @Override
    public void cleanTable() {
        String sql = "CLEAR TABLE IF EXISTS posts";
    }

    @Override
    public void savePost(Post post) {
        String sql = """
                INSERT INTO posts (image,description, created_date, user_id)
                VALUES (?,?, ?, ?)
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, post.getImage());
            preparedStatement.setString(2, post.getDescription());
            preparedStatement.setDate(3, Date.valueOf(post.getCreatedDate()));
            preparedStatement.setLong(4, post.getUserId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully saved post");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setImage(resultSet.getString("image"));
                post.setDescription(resultSet.getString("description"));
                post.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                post.setUserId(resultSet.getLong("user_id"));
                posts.add(post);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return posts;
    }

    @Override
    public Post getPostById(Long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post = new Post();
            while (resultSet.next()){
                post.setImage(resultSet.getString("image"));
                post.setDescription(resultSet.getString("description"));
                post.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                post.setUserId(resultSet.getLong("user_id"));
                return post;
            }

    }catch (SQLException e){
        System.out.println(e.getMessage());}
        return null;
    }

    @Override
    public void updatePost(Long id, Post newPost) {
        String sql = "UPDATE posts SET image = ?,description = ?,created_date =? , user_id =? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,newPost.getImage());
            preparedStatement.setString(2, newPost.getDescription());
            preparedStatement.setDate(3, Date.valueOf(newPost.getCreatedDate()));
            preparedStatement.setLong(4, newPost.getUserId());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletePost(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("""
         DELETE FROM posts WHERE id = ?;
         """)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Long countPostsByUser(Long id) {
        String sql = "SELECT COUNT(*) FROM posts WHERE user_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong(1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0L;
    }
}
