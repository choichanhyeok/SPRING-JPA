package com.hello.core.autowired;

import com.hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{
        @Autowired(required = false)            // 의존관계 없으면 메서드 자체가 호출이 안됨
        public void setNoBean1(Member noBean1){ // 얘는 없는거를 아무거나 집어 넣은거, 스프링이 관리하는 빈이 아닌거
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired                              // 의존관계가 없으면 호출은 되지만 null을 리턴
        public void SetNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired                              // 의존관계가 없으면 Optional.empty라는 것으로 넘겨줌
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
