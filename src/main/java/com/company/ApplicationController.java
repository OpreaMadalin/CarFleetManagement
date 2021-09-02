package com.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/inde.jsp")
    public String goCars() {
        return "inde";
    }

    @GetMapping("/index")
    public String goHome() {
        return "index";
    }

    @GetMapping("/cars")
    public String goProfile() {
        return "cars";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginBP")
    public String loginBP() {
        return "loginBP";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
