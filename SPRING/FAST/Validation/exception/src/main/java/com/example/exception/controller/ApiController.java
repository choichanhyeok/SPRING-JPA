package com.example.exception.controller;


import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @RequestMapping("")         // @RequestParam: 리퀘스트 없어도 동작하는데, 쟤는 널
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        int a = 10+age;
        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){            // @Vaild를 이용해 인자로 받는 User의 Validate를 추적,
        System.out.println(user);
        return user;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // 우선 순위는 컨트롤러의 예외처리 핸들러가 가져감 **
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
