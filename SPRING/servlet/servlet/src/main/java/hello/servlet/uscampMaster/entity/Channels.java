package hello.servlet.uscampMaster.entity;

import lombok.Getter;

@Getter
public class Channels {
    private String masterName;
    private String youtubeUrl;

    public Channels(String masterName, String youtubeUrl){
        this.masterName = masterName;
        this.youtubeUrl = youtubeUrl;
    }
}
