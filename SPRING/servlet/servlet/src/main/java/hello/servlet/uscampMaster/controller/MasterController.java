package hello.servlet.uscampMaster.controller;

import hello.servlet.uscampMaster.entity.Master;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MasterController{

    @GetMapping("/masters")
    ResponseEntity<List<Master>> getMasters(){
        List<Master> Masters = new ArrayList<Master>();

        Master masterA = new Master("MS-013", "강환국", "퀀트투자의 아이콘", "https://academy.tantanselect.com/images/us_campus/master/big/%EA%B0%95%ED%99%98%EA%B5%AD.png");
        Master masterB = new Master("MS-028", "김정수", "종목왕 김정수", "https://academy.tantanselect.com/images/us_campus/master/big/%EA%B9%80%EC%A0%95%EC%88%98.png");

        Masters.add(masterA);
        Masters.add(masterB);

        return ResponseEntity.ok().body(Masters);
    }

    @GetMapping("/channels/{masterId}")
    ResponseEntity<List<String>> getChannelUrls(){
        List<String> channels = new ArrayList<String>();
        channels.add("https://www.youtube.com/watch?v=Bh-ysnN1mm0");
        channels.add("https://www.youtube.com/watch?v=Kjijjtxy3PQ");
        channels.add("https://www.youtube.com/watch?v=V8C4HXaPxbg");

        return ResponseEntity.ok().body(channels);
    }
}