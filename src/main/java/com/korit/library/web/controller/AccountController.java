package com.korit.library.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    //로그인 페이지
    @GetMapping("/login")
    public String login(){

        return "account/login";
    }
    //회원가입 페이지
    @GetMapping("/register")
    public String register(){

        return "account/register";
    }
}
