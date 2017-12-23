package com.example.bobby.blooddonation.utility;

public class testimonialutility {
    private String pic, testimonial, email,state,date,district,name;

    public testimonialutility() {
    }

    public testimonialutility(String pic, String testimonial, String email, String state, String date, String district, String name) {
        this.pic = pic;
        this.testimonial = testimonial;
        this.email = email;
        this.state=state;
        this.date=date;
        this.district=district;
        this.name=name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String Pic) {
        this.pic = pic;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String State) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = date;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String District) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = name;
    }
}