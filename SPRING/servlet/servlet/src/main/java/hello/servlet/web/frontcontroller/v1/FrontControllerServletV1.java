package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name ="FrontControllerServletV1", urlPatterns = "/front-controller/v1/*") // v1에 뭐가 들어와도 이 서블릿이 호출
public class FrontControllerServletV1 extends HttpServlet {
    
    // TODO 1. Mapping 정보 만들기
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("FrontControllerServletV1.service");

        // TODO 2. Parse the request URI and get the Controller
        String requestURI = req.getRequestURI();

        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);

        // TODO 3. if controllerV1 is empty, Set response value to 404
        if (controllerV1 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // TODO 4. if controller V1 is not empty, call the appropriate controller
        controllerV1.process(req, resp);
    }
}
