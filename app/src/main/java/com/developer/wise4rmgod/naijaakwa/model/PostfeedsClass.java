package com.developer.wise4rmgod.naijaakwa.model;

public class PostfeedsClass {
      String id;
      String img;
      String message;
      String date;
      String backgroundcolor;
      String fullname;
  public PostfeedsClass(){

  }
    public PostfeedsClass(String id, String img, String message, String date, String backgroundcolor, String fullname) {
        this.id = id;
        this.img = img;
        this.message = message;
        this.date = date;
        this.backgroundcolor = backgroundcolor;
        this.fullname = fullname;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBackgorundcolor() {
        return backgroundcolor;
    }

    public void setBackgorundcolor(String backgorundcolor) {
        this.backgroundcolor = backgorundcolor;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
