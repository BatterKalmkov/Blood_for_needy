package com.example.bobby.blooddonation.utility;

public class blogutility {
    private String title, time,source, content,pic;

    public blogutility() {
    }

    public blogutility(String title, String source, String time, String content, String pic) {
        this.title = title;
        this.source = source;
        this.time = time;
        this.content=content;
        this.pic=pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}

