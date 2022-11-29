package com.politics_moorim.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class PostController {

    @GetMapping("")
    public String testGet(){
        return "Hi, hyeok i'm your first commercial project";
    }



}
