package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Principal principal, ModelMap model){
        String name = principal != null ? principal.getName() : null;
        model.addAttribute("user", name);
        return "index";
    }
}