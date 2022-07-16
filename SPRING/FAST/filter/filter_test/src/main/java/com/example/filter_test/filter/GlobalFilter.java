package com.example.filter_test.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@Slf4j
//@Component                          // 스프링에서 bean으로 관리하기 위해 컴포넌트 설정 (전부 적용할 때)
@WebFilter(urlPatterns = "/api/user/*") // 특정 url에 적용할 때, Component 대신 WebFilter로 직접 적용 가능
public class GlobalFilter implements Filter {

    @Override  // Filter를 상속받아, doFilter 사용을 위해 override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;     // 인자로 받은 request를 HttpServletRequest로 형변환
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response; // 인자로 받은 response를 HttpServletResponse로 형변환
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);      // 인자로 받은 request를 HttpServletRequest로 형변환
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response); // 인자로 받은 response를 HttpServletResponse로 형변환

        chain.doFilter(httpServletRequest, httpServletResponse); // ContentCachingResponseWrapper의 경우, 생성자에서 read 없음 dofilter를 통해 값을 읽어고기 때문에 관련 내용 아래로 다 뺴줘
        String url = httpServletRequest.getRequestURI();         // 위 내용에 대한 결과

        // 후처리, doFIlter 이후에 읽어야함
        // req
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url: {}, request body: {]", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();
        httpServletResponse.copyBodyToResponse();               // 위 과정에서 body를 읽어 버려, api 요청시 클라이언트에 body값이 비게 된다. 이 문제를 해결하기 위해


        log.info("response status: {}, responseBody: {}", httpStatus, resContent);

    }
}
