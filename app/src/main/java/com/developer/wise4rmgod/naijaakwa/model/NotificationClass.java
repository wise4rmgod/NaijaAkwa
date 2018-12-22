package com.developer.wise4rmgod.naijaakwa.model;

public class NotificationClass {
    public String id;
    public String userid;
    public String rating;
    public String img;
    public String specialization;

    public NotificationClass (){}

    public NotificationClass(String id, String userid, String rating, String img, String specialization, String fullname) {
        this.id = id;
        this.userid = userid;
        this.rating = rating;
        this.img = img;
        this.specialization = specialization;
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String fullname;
}
