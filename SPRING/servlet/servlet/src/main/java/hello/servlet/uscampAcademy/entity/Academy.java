package hello.servlet.uscampAcademy.entity;

import lombok.Getter;

@Getter
public class Academy{
    private String academyCode;
    private String academySubject;
    private String academyExplain;
    private String academyTag;
    private String type;

    public Academy(String academyCode, String academySubject, String academyExplain, String academyTag, String type){
        this.academyCode = academyCode;
        this.academySubject = academySubject;
        this.academyExplain = academyExplain;
        this.academyTag = academyTag;
        this.type = type;
    }
}
