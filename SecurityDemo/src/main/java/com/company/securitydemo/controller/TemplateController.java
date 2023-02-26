package com.company.securitydemo.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginView(){
        return "login";
    }

    @GetMapping("courses")
    public String getCourseView(){
        return "courses";
    }
}
