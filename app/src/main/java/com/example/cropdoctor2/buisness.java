package com.example.cropdoctor2;

/**
 * Created by Lincoln on 15/01/16.
 */
public class buisness {
    private String title, desc, link;

    public buisness() {
    }

    public buisness(String title,String desc, String link) {
        this.title = title;
        this.desc = desc;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
