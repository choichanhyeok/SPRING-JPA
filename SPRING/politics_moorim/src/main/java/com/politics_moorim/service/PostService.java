package com.politics_moorim.service;


import com.politics_moorim.domain.Post;
import com.politics_moorim.repository.PostRepository;
import com.politics_moorim.request.PostCreate;
import com.politics_moorim.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")); // 가능하면 옵셔널 같은 데이터는 가져와서 바로 꺼내는게 좋음

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(Pageable pageable){
//        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
        return postRepository.findAll(pageable).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());

//        return postRepository.findAll().stream() // (2) 위 방식의 개선 전 코드
//                .map(post -> new PostResponse(post))
//                .collect(Collectors.toList());

//        return postRepository.findAll().stream() // (1) 위 방식의 개선 전 코드
//                .map(post ->
//                    PostResponse.builder()
//                            .id(post.getId())
//                            .title(post.getTitle())
//                            .content(post.getContent())
//                            .build())
//                .collect(Collectors.toList());
    }
}
