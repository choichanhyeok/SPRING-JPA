package com.example.exception.controller;


import com.example.exception.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")
@Validated
public class ApiController {

    @RequestMapping("")         // @RequestParam: 리퀘스트 없어도 동작하는데, 쟤는 널
    public User get(
            @Size(min = 2)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age){
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

//    @ExceptionHandler(value = MethodArgumentNotValidException.class) // 우선 순위는 컨트롤러의 예외처리 핸들러가 가져감 **
//    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
}
