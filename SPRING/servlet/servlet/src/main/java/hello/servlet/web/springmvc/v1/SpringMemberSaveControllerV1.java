package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SpringMemberSaveControllerV1 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response){

        // TODO 1. 넘어온 param 값들을 각 변수에 파싱
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // TODO 2. 파싱된 값들을 인자로 Member 객체 생성, .save 메서드를 이용해 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // TODO 3. save-result라는 식별값으로 Model-result 객체 생성, ModelView의 model에 member 객체 저장
        ModelAndView mv = new ModelAndView("save-result");
        // mv.getModel().put("member", member);
        mv.addObject("member", member);
        return mv;
    }
}
