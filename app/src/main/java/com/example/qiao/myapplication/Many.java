package com.example.qiao.myapplication;

import org.litepal.crud.DataSupport;

public class Many extends DataSupport {
    private String text;
    private String path;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}