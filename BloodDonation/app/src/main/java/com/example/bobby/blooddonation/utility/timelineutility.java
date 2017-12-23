package com.example.bobby.blooddonation.utility;

public class timelineutility {
    private String bloodgroup, time,source, content,state,district;

    public timelineutility() {
    }

    public timelineutility(String bloodgroup, String source, String time, String content,String state, String district) {

        this.source = source;
        this.time = time;
        this.content=content;
        this.bloodgroup=bloodgroup;
        this.state=state;
        this.district=district;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

