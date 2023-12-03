package com.example.demo;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Customers {
    @JsonProperty("USERNAME")
    private String username;

    @JsonProperty("PASSWORD")
    private String password;

    @JsonProperty("NAME")
    private String name;

    @JsonProperty("BIRTH")
    private Date birth;

    @JsonProperty("PHONE_NUMBER")
    private String phoneNumber;

    @JsonProperty("EMAIL")
    private String email;

    @JsonProperty("MEMBERSHIP_ID")
    private int membershipId = 0;


    public Customers(String username, String password, String name, Date birth, String phoneNumber, String email, int membershipId){
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipId = membershipId;
    }

    public Customers(){
        this.username = null;
        this.password = null;
        this.name = null;
        this.birth = null;
        this.phoneNumber = null;
        this.email = null;
        this.membershipId = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

}