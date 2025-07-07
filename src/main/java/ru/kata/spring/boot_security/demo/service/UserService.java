package ru.kata.spring.boot_security.demo.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    void deleteUser(Long id);
    void createUser(User user, List<Long> roleIds);
    User findById(Long id);
    boolean updateUser(Long id, User formUser, Long[] roleIds, String rawPassword);
}
