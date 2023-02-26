package com.politics_moorim.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.politics_moorim.domain.Post;
import com.politics_moorim.repository.PostRepository;
import com.politics_moorim.request.PostCreate;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest
@AutoConfigureMockMvc // (2) SpringbootTest만 이용하면 MockMvc를 주입받을 수 없어 @WebMvcTest 안에서 MockMvc를 긁어오는 @AutoCOnfigureMockMvc를 직접 적용
@SpringBootTest       // (1) WebMvcTest로는 a서비스, 레포지토리에 대한 테스트를 할 수 없음
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 사용자의 게시글 저장")
    void postCallTest() throws Exception {
        // given
        PostCreate request = PostCreate.builder().title("제목입니다.").content("내용입니다.").build();
        String json = objectMapper.writeValueAsString(request);


        // expected
        mockMvc.perform(post("/posts")
                            .contentType(APPLICATION_JSON)
                            .content(json)
                        )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts로 post 요청시 title 값을 필수 !")
    void postVaildTest() throws Exception {
        // given
        PostCreate request = PostCreate.builder().title(null).content("내용입니다.").build();
        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts로 post 요청시 DB에 값이 저장된다.")
    void postDbInsertTest() throws Exception {
        // given
        PostCreate request = PostCreate.builder().title("제목입니다.").content("내용입니다.").build();
        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
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


    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception{
        // given
        Post post = Post.builder()
                .title("123456789012345")
                .content("bar")
                .build();
        postRepository.save(post);

        // expected (when과 then을 섞은 정도의 의미)
        mockMvc.perform(get("/posts/{postId}", post.getId())
                    .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("1234567890"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test5() throws Exception{
        // given
        Post post1 = postRepository.save(
                Post.builder()
                .title("title_1")
                .content("content_1")
                .build());

        Post post2 = postRepository.save(
                Post.builder()
                .title("title_2")
                .content("content_2")
                .build()
        );

        // expected (when과 then을 섞은 정도의 의미)
        mockMvc.perform(get("/posts")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id").value(post1.getId()))
                .andExpect(jsonPath("$[0].title").value("title_1"))
                .andExpect(jsonPath("$[0].content").value("content_1"))
                .andExpect(jsonPath("$[1].id").value(post2.getId()))
                .andExpect(jsonPath("$[1].title").value("title_2"))
                .andExpect(jsonPath("$[1].content").value("content_2"))
                .andDo(print());
    }
}