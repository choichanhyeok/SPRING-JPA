package hello.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;


@Slf4j
@Controller
public class RequestBodyStringController {

    @RequestMapping(value="/request-body-string-v1", method = RequestMethod.POST)
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info(messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyString2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {} ", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyString3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {} ", messageBody);

        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v4")
    public ResponseEntity<String> requestBodyString4(RequestEntity<String> requestEntity){
        String messageBody = requestEntity.getBody();
        return new ResponseEntity<String>("ok", HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public String requestBodyString5(@RequestBody String messageBody){
        return "ok";
    }
}