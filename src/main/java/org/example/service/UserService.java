package org.example.service;

import org.example.models.User;

import java.util.List;

public interface UserService {
    //todo ddl

    void createUserTable();

    void dropUserTable();

    void clearTable();

    //todo dml

    void createUser(User user);

    List<User> getUsers();

    User getUserById(Long id);

    void updateUser(Long id,User newUser);

    void deleteUser(Long id);

    List<User> searchusersByName(String username);
}
