package com.example.qiao.myapplication;

public class VideoTitle {
    private String chapter;
    private String name;
  private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VideoTitle(String name) {
        this.name = name;
    }
public VideoTitle(String chapter, String name, String time) {
        this.chapter = chapter;
        this.name = name;
        this.time = time;
    }
}
