package com.politics_moorim.controller;

import com.politics_moorim.domain.Post;
import com.politics_moorim.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest
@AutoConfigureMockMvc // (2) SpringbootTest만 이용하면 MockMvc를 주입받을 수 없어 @WebMvcTest 안에서 MockMvc를 긁어오는 @AutoCOnfigureMockMvc를 직접 적용
@SpringBootTest       // (1) WebMvcTest로는 서비스, 레포지토리에 대한 테스트를 할 수 없음
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PostRepository postRepository;
    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 사용자의 게시글 저장")
    void postCallTest() throws Exception {

        mockMvc.perform(post("/posts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\": \"제목입니다.\", \"content\":  \"내용입니다.\"}")
                        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/posts로 post 요청시 title 값을 필수 !")
    void postVaildTest() throws Exception {

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": null, \"content\":  \"내용입니다.\"}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청 입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts로 post 요청시 DB에 값이 저장된다.")
    void postDbInsertTest() throws Exception {
        // when
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\", \"content\":  \"내용입니다.\"}")
                )
                .andExpect(status().isOk())
                .andDo(print());
        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
    }

    @Test
    @DisplayName("테스트용 get request 호출을 테스트 (설명에 수미상관법 적용)")
    void testGetTest() throws Exception {
        String expectedWord = "Hi, hyeok i'm your first commercial project";
        // expected
        mockMvc.perform(get("/posts/testcall"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hi, hyeok i'm your first commercial project"))
                .andDo(print());
    }
}