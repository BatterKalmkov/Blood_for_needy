package com.example.bobby.blooddonation.utility;

public class eventutility {
    private String venue, time,contactperson, event,date, description,mobile,latitude,longitude;

    public eventutility() {
    }

    public eventutility(String venue, String time, String contactperson, String event, String date, String description, String mobile, String latitude,String longitude) {
        this.venue = venue;
        this.time = time;
        this.contactperson = contactperson;
        this.event=event;
        this.date=date;
        this.description=description;
        this.mobile=mobile;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}


