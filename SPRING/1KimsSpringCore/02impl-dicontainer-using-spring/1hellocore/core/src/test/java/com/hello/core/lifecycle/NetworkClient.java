package com.hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }


    @PostConstruct
    public void init(){ // 이름도 맘대로 작성
        // 의존관계 주입 끝날 때 실행
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){ // 컨테이너가 내려가 종료하기 전에 싱글톤 빈들이 하나씩 죽을 때,
        System.out.println("NetworkClient.destroy");
        disconnect(); // close를 안전하게 호출
    }
}
