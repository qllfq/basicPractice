package com.example.qiao.myapplication;

class SongInfo {
    private String number;
    private String song;
    private String time;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public SongInfo(String number,String song,String time){
        this.number = number;
        this.song = song;
        this.time = time;
    }
}




