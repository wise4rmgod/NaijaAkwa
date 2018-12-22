package com.developer.wise4rmgod.naijaakwa.model;

public class CommentsClass {
    String id;
    String date;
    String message;
    String fullname;
    String img;

    public CommentsClass(){}

    public CommentsClass(String id, String date, String message, String fullname, String img) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.fullname = fullname;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
