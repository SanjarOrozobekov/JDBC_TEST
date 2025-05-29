package org.example.dao.daoImpl;

import org.example.config.JdbcConfig;
import org.example.dao.CommentDao;
import org.example.models.Comment;
import org.example.models.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private Connection connection = JdbcConfig.getConnection();
    @Override
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS comments (
                id SERIAL PRIMARY KEY,
                text TEXT NOT NULL,
                comment_date DATE NOT NULL,
                post_id INT REFERENCES posts(id),
                user_id INT REFERENCES users(id))
                """;
        try(Statement statement = connection.createStatement()){
            int i = statement.executeUpdate(sql);
            System.out.println(i);
            System.out.println("Successfully created table");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS comments";
    }

    @Override
    public void cleanTable() {
        String sql = "CLEAR TABLE IF EXISTS comments";
    }

    @Override
    public void saveComment(Comment comment) {
        String sql = """
   INSERT INTO comments (text, comment_date, post_id, user_id)
   VALUES (?,?,?,?)
   """;
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, comment.getText());
            statement.setDate(2, Date.valueOf(comment.getCommentDate()));
            statement.setLong(3, comment.getPostId());
            statement.setLong(4, comment.getUserId());
            statement.executeUpdate();
            System.out.println("Comment created");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setCommentDate(resultSet.getDate("comment_date").toLocalDate());
                comment.setPostId(resultSet.getLong("post_id"));
                comment.setUserId(resultSet.getLong("user_id"));
                comments.add(comment);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Comment comment = new Comment();
            while (resultSet.next()){
            comment.setText(resultSet.getString("text"));
            comment.setCommentDate(resultSet.getDate("comment_date").toLocalDate());
            comment.setPostId(resultSet.getLong("post_id"));
            comment.setUserId(resultSet.getLong("user_id"));
            return comment;
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());}
        return null;
    }

    @Override
    public void updateComment(Long id, Comment newComment) {
        String sql = "UPDATE comments SET text = ?,comment_date = ?,post_id =? , user_id =? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, newComment.getText());
            preparedStatement.setDate(2, Date.valueOf(newComment.getCommentDate()));
            preparedStatement.setLong(3, newComment.getPostId());
            preparedStatement.setLong(4, newComment.getUserId());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCommentById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("""
         DELETE FROM comments WHERE id = ?;
         """)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Comment> findRecentCommentsByPost(Long postId, int limit) {
        List<Comment> comments = new ArrayList<>();
        String sql = """
                SELECT * FROM comments
                WHERE post_id = ?
                ORDER BY comment_date DESC
                LIMIT ?
                """;
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, postId);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setText(resultSet.getString("text"));
                comment.setCommentDate(resultSet.getDate("comment_date").toLocalDate());
                comment.setPostId(resultSet.getLong("post_id"));
                comment.setUserId(resultSet.getLong("user_id"));
                comments.add(comment);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return comments;
    }

    @Override
    public Long countCommentsByPost(Long postId) {
        String sql = "SELECT COUNT(*) FROM comments WHERE post_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,postId);
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
