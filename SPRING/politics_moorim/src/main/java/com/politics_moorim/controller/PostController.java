package com.politics_moorim.controller;

import com.politics_moorim.request.PostCreate;
import com.politics_moorim.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/testcall")
    public String testGet(){
return "Hi, hyeok i'm your first commercial project";
    }

    @PostMapping("")
    public Map<String, String> post(@RequestBody @Valid PostCreate request){
        postService.write(request);
        return Map.of();
    }

}
