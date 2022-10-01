package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap){

        // TODO 1. 넘어온 param 값들을 각 변수에 파싱
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // TODO 2. 파싱된 값들을 인자로 Member 객체 생성, .save 메서드를 이용해 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // TODO 3. save-result라는 식별값으로 Model-result 객체 생성, ModelView의 model에 member 객체 저장
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member); 
        return mv;
    }
}
