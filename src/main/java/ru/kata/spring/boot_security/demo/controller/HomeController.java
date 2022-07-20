package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Principal principal, Model model){
        model.addAttribute("user", principal.getName());
        return "index";
    }
}