package com.hello.core.web;


import com.hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
     private final MyLogger myLogger; // => 기존에 오류 났던 코드 그냥 써버림
//    private final ObjectProvider<MyLogger> myLoggerProvider; // MyLogger를 주입 받는게 아니라 빈을 찾아주는 Provider를 초기화

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        // ObjectProvider을 사용하는 아래 코드 제거
//        MyLogger myLogger = myLogger.getObject(); // 필요한 시점에 DL을 이용해 MyLogger 빈의 의존성을 주입함
        System.out.println("mylog = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);                         // ** requestBean이 묶임을 보기 위해 컨트롤러에 쓰레드 지연시킴
        logDemoService.logic("testId");
        return "OK";
    }
}
