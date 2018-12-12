package com.developer.wise4rmgod.naijaakwa.model;

public class RegisterBuyerClass {
    String id;
    String email;
    String password;
    String state;
    String sex;
    String role;
    String address;
    String city;
    String full_name;
    String img;

    public RegisterBuyerClass(String id, String email, String password, String state, String sex, String role, String address, String city, String full_name, String img) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.state = state;
        this.sex = sex;
        this.role = role;
        this.address = address;
        this.city = city;
        this.full_name = full_name;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
