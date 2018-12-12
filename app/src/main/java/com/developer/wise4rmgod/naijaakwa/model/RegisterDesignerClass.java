package com.developer.wise4rmgod.naijaakwa.model;

public class RegisterDesignerClass {
    public String id;
    public String email;
    public String password;
    public String state;
    public String sex;
    public String level;
    public String specialization;
    public String role;
    public String subscription;
    public String address;
    public String city;
    public String fullname;
    public String phone;
    public String img;

    public RegisterDesignerClass(){

    }

    public RegisterDesignerClass(String id, String email, String password, String state, String sex, String level, String specialization, String role, String subscription, String address, String city, String fullname, String phone, String img) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.state = state;
        this.sex = sex;
        this.level = level;
        this.specialization = specialization;
        this.role = role;
        this.subscription = subscription;
        this.address = address;
        this.city = city;
        this.fullname = fullname;
        this.phone = phone;
        this.img = img;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getState() {
        return state;
    }

    public String getSex() {
        return sex;
    }

    public String getLevel() {
        return level;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getRole() {
        return role;
    }

    public String getSubscription() {
        return subscription;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getImg() {
        return img;
    }
}
