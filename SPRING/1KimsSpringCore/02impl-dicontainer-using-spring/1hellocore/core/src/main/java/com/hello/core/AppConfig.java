package com.hello.core;

import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.FixDiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.member.MemoryMemberRepository;
import com.hello.core.order.OrderService;
import com.hello.core.order.OrderServiceImpl;


/**
 * DIP (의존관계 역전의 원칙을 지키기 위해, 실제 동작에 필요한 구현 객체를 Appconfig에서 생성해줌.)
 * @author chanhyeok
 */
public class AppConfig {

    // 멤버 서비스에서 필요한 의존성: MemberRepository 인터페이스에서,
    public MemberService memberService(){
        return new MemberServiceImpl(getMemberRepository());
    }

    private MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }

    public DiscountPolicy getDiscountPolicy(){
        return new RateDiscountPolicy();
    }

}


// 1. 어떤 클래스에서 인터페이스가 아닌 클래스 객체를 직접 생성하면 무조건 DIP(의존관계역전) 위반인가?
// 2. 인터페이스 분리 원칙은
// 3. 스프링에서 Impl 쓰는 이유,인터페이스를 @Service로 등록하라는건가? 그게 아니면 IoC를 통해 의존성 주입을 알아서 해주는데 그게 필요한가?