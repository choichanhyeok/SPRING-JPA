package com.hello.core;

import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        
        // MemberService memberService = new MemberServiceImpl(); => 아래로 개선
        MemberService memberService = appConfig.memberService();

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+ memberA.getName());
        System.out.println("fine member = "+ findMember.getName());
    }
}
