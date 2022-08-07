package com.hello.core.order;

import com.hello.core.AppConfig;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void Order (){
        Long memberId = 1L;
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(1L, "Dragon", 5000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
