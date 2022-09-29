package hello.servlet.uscampAcademy.controller;


import hello.servlet.uscampAcademy.entity.Academy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AcademyController {

    @GetMapping("/academy/{type}")
    public ResponseEntity<List<Academy>> getAcademys(){
        List<Academy> academies = new ArrayList<>();

        Academy academyA = new Academy("academy107", "홍춘욱 달러투자 스터디", "경제 전문가 홍춘욱의 유일한 직강", "US#홍춘욱#달러#시크릿비밀노트", "US");
        Academy academyB = new Academy("academy108", "똔누와 함께하는 슬기로운 재테크 스터디", "1억 만들기 100일 프로젝트", "US#재테크#챌린지", "US");

        academies.add(academyA);
        academies.add(academyB);
        return ResponseEntity.ok().body(academies);
    }

    @GetMapping("/academy")
    public ResponseEntity<List<Academy>> getAcademysAll(){
        List<Academy> academies = new ArrayList<>();

        Academy academy0 = new Academy("academy103", "한세구와 함께하는 실전 투자스쿨", "대한민국 증권가의 살아있는 전설", "815#실전투자#운용전략", "815");
        Academy academyA = new Academy("academy107", "홍춘욱 달러투자 스터디", "경제 전문가 홍춘욱의 유일한 직강", "US#홍춘욱#달러#시크릿비밀노트", "US");
        Academy academyB = new Academy("academy108", "똔누와 함께하는 슬기로운 재테크 스터디", "1억 만들기 100일 프로젝트", "US#재테크#챌린지", "US");

        academies.add(academy0);
        academies.add(academyA);
        academies.add(academyB);

        return ResponseEntity.ok().body(academies);
    }
}
