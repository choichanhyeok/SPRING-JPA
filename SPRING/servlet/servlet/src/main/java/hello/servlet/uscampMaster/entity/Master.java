package hello.servlet.uscampMaster.entity;

import lombok.Getter;

@Getter
public class Master {
    private String masterCode;
    private String masterName;
    private String masterExplain;
    private String masterImgUrl;

    public Master(String masterCode, String masterName, String masterExplain, String masterImgUrl){
        this.masterCode = masterCode;
        this.masterName = masterName;
        this.masterExplain = masterExplain;
        this.masterImgUrl = masterImgUrl;
    }
}
