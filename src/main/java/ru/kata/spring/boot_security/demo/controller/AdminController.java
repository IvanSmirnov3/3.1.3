package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminDashboard(@AuthenticationPrincipal User user,
                                 @RequestParam(value = "editUserId", required = false) Long editUserId,
                                 Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("newUser", new User());

        if (editUserId != null) {
            model.addAttribute("editUser", userService.findById(editUserId));
        } else {
            model.addAttribute("editUser", new User());
        }

        return "admin";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("newUser") User user,
                             @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        userService.createUser(user, roleIds);
        return "redirect:/admin";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable("id") Long id,
                             @ModelAttribute("editUser") User formUser,
                             @RequestParam(value = "roles", required = false) List<Long> roleIds,
                             @RequestParam(value = "password", required = false) String rawPassword) {
        userService.updateUser(id, formUser, roleIds, rawPassword);
        return "redirect:/admin";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
