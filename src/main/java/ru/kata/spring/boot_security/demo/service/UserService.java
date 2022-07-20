package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> users();
    void create(User user);
    void edit(int id, User user);

    User findUserById(int id);
    User findUserByName(String name);
    void delete(int id);
}