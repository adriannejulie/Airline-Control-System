package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Crew{

    @JsonProperty("USERNAME")
    private int username;

    @JsonProperty("NAME")
    private String name;

    public Crew(int userlogin, String name) {
        this.username = userlogin;
        this.name = name;
    }


}