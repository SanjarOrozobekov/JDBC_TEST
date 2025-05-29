package org.example.dao.daoImpl;

import org.example.config.JdbcConfig;
import org.example.dao.UserDao;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoIml implements UserDao {

    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void createUserTable() {
        String sql = """
               CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY ,
                username VARCHAR(50),
                email VARCHAR(50),
                password VARCHAR(50))
               """;
        try(Statement statement = connection.createStatement()){
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUserTable() {
        String sql = "DROP TABLE users";
    }

    @Override
    public void clearTable() {
        String sql = "clear table users";
    }

    @Override
    public void createUser(User user) {
        String sql = """
                INSERT INTO users (username, email, password)
                VALUES (?, ?, ?)
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("User created");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                User user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        String sql = "select * from users where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
                User user = new User();
                while(resultSet.next()) {
                    user.setUserName(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }
                return user;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateUser(Long id, User newUser) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,newUser.getUserName());
            preparedStatement.setString(2,newUser.getEmail());
            preparedStatement.setString(3,newUser.getPassword());
            preparedStatement.setLong(4,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("""
         DELETE FROM users WHERE id = ?;
         """)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> searchusersByName(String username) {
        List<User> users = new ArrayList<>();
        String sql = "select * from users where username = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }
}
