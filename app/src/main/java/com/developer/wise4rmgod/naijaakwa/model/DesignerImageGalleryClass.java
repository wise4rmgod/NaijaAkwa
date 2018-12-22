package com.developer.wise4rmgod.naijaakwa.model;

public class DesignerImageGalleryClass {
    public String id;
    public String img;
    public String description;
    public String title;
    public String date;
    public String userid;

    public DesignerImageGalleryClass(){}

    public DesignerImageGalleryClass(String id, String img, String description, String title, String date, String userid) {
        this.id = id;
        this.img = img;
        this.description = description;
        this.title = title;
        this.date = date;
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
