package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public String userPage(Model model, Principal principal){
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "user";
    }
    @GetMapping("/admin/add-user")
    public String showSignUpForm(User user){
        return "add-user";
    }
    @PostMapping("admin/add-user")
    public String addUser(@Validated User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-user";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin")
    public String showUserList(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }
    @GetMapping("admin/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "update-user";
    }
    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable Long id, @Validated User user, BindingResult result){
        if (result.hasErrors()){
            user.setId(id);
            return "update-user";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUSer(id);
        return "redirect:/admin";
    }

}
