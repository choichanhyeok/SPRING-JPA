package com.politics_moorim.service;


import com.politics_moorim.domain.Post;
import com.politics_moorim.repository.PostRepository;
import com.politics_moorim.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        Post post = Post.builder()
                    .title(postCreate.getTitle())
                    .content(postCreate.getContent())
                    .build();

        postRepository.save(post);
    }

    public Post get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")); // 가능하면 옵셔널 같은 데이터는 가져와서 바로 꺼내는게 좋음
        return post;
    }
}
