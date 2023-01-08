package com.politics_moorim.controller;

import com.politics_moorim.request.RoarCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roar")
@Slf4j
public class PostController {
    // HTTP Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT

    @GetMapping("/testcall")
    public String testGet(){
        return "Hi, hyeok i'm your first commercial project";
    }

    @PostMapping("")
    public Map<String, String> roarToWorld(@RequestBody @Valid RoarCreate param, BindingResult result){
        // 데이터 검증의 이유
        // 1. client 개발자가 깜빡할 수 있다. (휴먼 에러)
        // 2. client bug로 값이 누락될 수 있다.
        // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        // 5. 서버 개발자의 편안함을 위해서

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firestFieldError = fieldErrors.get(0);

            String invalidFieldName = firestFieldError.getField();       // title
            String errorMessage = firestFieldError.getDefaultMessage();  // error message

            Map<String, String> error = new HashMap<>();
            error.put(invalidFieldName, errorMessage);
            return error;
        }

        return Map.of();
    }

}
