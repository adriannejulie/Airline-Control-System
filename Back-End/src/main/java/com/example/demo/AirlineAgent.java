package com.example.demo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AirlineAgent{

    @JsonProperty("USERNAME")
    int username;

    @JsonProperty("PASSWORD")
    String password;
    public AirlineAgent(int username, String password) {        
        this.username = username;
        this.password = password;
    }
}