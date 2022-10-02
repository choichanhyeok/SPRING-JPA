package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "FrontControllerservletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerservletV4 extends HttpServlet {

    // TODO 1. 매핑 정보 등록
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerservletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    }

    @Override   // HttpServlet 호출시 service
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO 2. 요청 url 파싱해서, 미리 매핑한 정보로 조회해 컨트롤러 호출
        String requestURI = req.getRequestURI();
        ControllerV4 controllerV4 = controllerV4Map.get(requestURI); // ex. MemberSeaveControllerV3
        // TODO 2-1. 예외처리: 해당 url과 매핑된 컨트롤러가 없을 경우
        if (controllerV4 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // TODO 3. request의 parame 이름과 값을 추출해서 paramMap로 만들어줌.
        Map<String, String> paramMap = createParamMap(req);

        // TODO 4. model 값을 받을 hashMap 객체 생성
        Map<String, Object> model = new HashMap<>();

        // TODO 4. 기존처럼 request, response가 아닌, 파람만 추출한 paramMap를 넘겨줌. 이 때 model 정보를 받아줄 객체도 넘겨줌.
        String viewName = controllerV4.process(paramMap, model);

        // TODO 5. MyView로 RequestDispatcher.forword로 뷰 띄워줌
        MyView view = viewResolver(viewName);

        view.render(model, req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
