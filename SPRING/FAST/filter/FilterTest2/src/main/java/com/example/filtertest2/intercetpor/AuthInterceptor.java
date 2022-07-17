package com.example.filtertest2.intercetpor;

import com.example.filtertest2.annotaion.Auth;
import com.example.filtertest2.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;


@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                        .query(request.getQueryString())
                        .build()
                        .toUri();

        log.info("request url : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation: {}", hasAnnotation);

        // 나의 서버는 모두 public으로 동작을 할 때,
        // Auth 권한을 가진 요청에 대해서는 (세션, 쿠키) 
        if(hasAnnotation){
            // 권한 체크
            String query = uri.getQuery();
            if(query.equals("name=steve")){
                return true;
            }
//            return false;
            throw new AuthException();//권한이 없을 경우
        }
        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz){ // 클래스라는 단어는 예약어라 표현 못해서 clazz로 많이 사용
        // resource: javascript, html,는 통과시켜주기 위해 아래 처리
        if (handler instanceof ResourceHttpRequestHandler){
            return true; // true는 모두 통과
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(null != handlerMethod.getMethodAnnotation(clazz)|| null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation 이 있을때는 true
            return true;
        }
        return false;       // 그 외에 것들은 통과시키지 않음
    }
}
