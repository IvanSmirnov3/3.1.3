package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    void deleteUser(Long id);
    void createUser(User user, List<Long> roleIds);
    User findById(Long id);
    boolean updateUser(Long id, User formUser, List<Long> roleIds, String rawPassword);
}
