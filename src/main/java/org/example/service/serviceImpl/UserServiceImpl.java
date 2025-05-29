package org.example.service.serviceImpl;

import org.example.models.User;
import org.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    public void createUserTable() {
        userServiceImpl.createUserTable();
    }

    @Override
    public void dropUserTable() {
        userServiceImpl.dropUserTable();
    }

    @Override
    public void clearTable() {
        userServiceImpl.clearTable();
    }

    @Override
    public void createUser(User user) {
        userServiceImpl.createUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userServiceImpl.getUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userServiceImpl.getUserById(id);
    }

    @Override
    public void updateUser(Long id, User newUser) {
        userServiceImpl.updateUser(id, newUser);
    }

    @Override
    public void deleteUser(Long id) {
        userServiceImpl.deleteUser(id);
    }

    @Override
    public List<User> searchusersByName(String username) {
        return userServiceImpl.searchusersByName(username);
    }
}
