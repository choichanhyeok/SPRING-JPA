package com.politics_moorim.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/roar로 post 요청시 사용자의 포효(게시글) 저장")
    void roarTest() throws Exception {

        mockMvc.perform(post("/roar")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\": \"제목입니다.\", \"content:\":  \"내용입니다.\"}")
                        )
                .andExpect(status().isOk())
                .andExpect(content().string("success"))
                .andDo(print());
    }

    @Test
    @DisplayName("/roar로 post 요청시 title 값을 필수 !")
    void roarTestVaild() throws Exception {

        mockMvc.perform(post("/roar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": null, \"content:\":  \"내용입니다.\"}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청 입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("테스트용 get request 호출을 테스트 (설명에 수미상관법 적용)")
    void testGetTest() throws Exception {
        String expectedWord = "Hi, hyeok i'm your first commercial project";
        // expected
        mockMvc.perform(get("/roar/testcall"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hi, hyeok i'm your first commercial project"))
                .andDo(print());
    }
}