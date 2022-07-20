package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String index(Model model) {
        List<User> user = userService.users();
        model.addAttribute("model", user);
        return "admin/index";
    }

    @GetMapping("/admin/create")
    public String create(Model model) {
        User user = new User();
        user.setRoles(roleService.roles());
        model.addAttribute("model", user);
        return "admin/create";
    }

    @PostMapping("/admin/create")
    public String create(User user) {
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("roles", roleService.roles());
        model.addAttribute("model", userService.findUserById(id));

        return "admin/edit";
    }

    @PostMapping("/admin/edit")
    public String edit(int id, User user) {
        userService.edit(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}