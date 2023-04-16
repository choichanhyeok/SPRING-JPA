package com.politics_moorim.service;

import com.politics_moorim.domain.Post;
import com.politics_moorim.repository.PostRepository;
import com.politics_moorim.request.PostCreate;
import com.politics_moorim.request.PostSearch;
import com.politics_moorim.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1(){
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // when
        postService.write(postCreate);

        // then
        Assertions.assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2(){
        // given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();

        postRepository.save(requestPost);

        // when
        PostResponse postResponse = postService.get(requestPost.getId());

        // then
        assertNotNull(postResponse);
        assertEquals("foo", postResponse.getTitle());
        assertEquals("bar", postResponse.getContent());
    }

    @Test
    @DisplayName("글 1 페이지 조회")
    void test3(){
        // given
        List<Post> requestPosts = IntStream.range(1, 30)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("최찬혁 제목 " + i)
                            .content("개발자중에 왕 " + i)
                            .build();
                })
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

//        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        // when
        List<PostResponse> posts = postService.getList(postSearch);

        // then
        assertEquals(10L, posts.size());
        assertEquals("최찬혁 제목 29", posts.get(0).getTitle());
        assertEquals("최찬혁 제목 25", posts.get(4).getTitle());
    }
}