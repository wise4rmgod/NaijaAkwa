package com.developer.wise4rmgod.naijaakwa.model;

public class DesignerGalleryCommentClass {
        String id;
        String userid;
        String img;
        String fullname;
        String message;

        public DesignerGalleryCommentClass(){

        }

    public DesignerGalleryCommentClass(String id, String userid, String img, String fullname, String message) {
        this.id = id;
        this.userid = userid;
        this.img = img;
        this.fullname = fullname;
        this.message = message;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
