package com.korit.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    //{두가지 주소 다이용가능하게 설정}
    @GetMapping({"","/index"})
    public String index(){

        return "index";
    }
}
