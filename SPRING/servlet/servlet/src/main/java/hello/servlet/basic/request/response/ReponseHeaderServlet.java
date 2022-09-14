package hello.servlet.basic.request.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReponseHeaderServlet", urlPatterns = "/response-header")
public class ReponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //[status-line]
        resp.setStatus(HttpServletResponse.SC_OK); // 시작 라인에 상태코드 200 내려주기
//
//        //[response-headers]
//        resp.setHeader("Content-Type", "text/plain;charset=utf-8");      // 헤더에 Content-Type text로
//        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  // 캐시 컨트롤을 이용해 캐시 완전히 무효화
//        resp.setHeader("Pragma", "no-cache");              // 과거 버전에도 캐시를 없애도록 추가 설정
//        resp.setHeader("my-header", "hello");

        //[Header 편의 메서드]
        content(resp);
        cookie(resp);
        redirect(resp);

        PrintWriter writer = resp.getWriter(); 
        writer.print("ok");                                          // 응답 body에 ok 써주기
    }
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8 라는 헤더가 확인되어야 한다.
        //Content-Length: 2 라는 헤더가 확인되어야 한다.
        //response.setHeader("Content-Type", "text/plain;charset=utf-8"); // 이렇게 말고 아래 두개처럼 메서드로 바로 설정 가능
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}