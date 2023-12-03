package com.example.demo;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Booked {

    @JsonProperty("USERNAME")
    private String username;

    @JsonProperty("FLIGHT_NUMBER")
    private String flightNumber;

    @JsonProperty("SEAT_TYPE")
    private String seatType;

    @JsonProperty("SEAT_NUMBER")
    private String seatNumber;

    @JsonProperty("INSURANCE_STATUS")
    private int insuranceStatus;

    @JsonProperty("ORIGIN")
    private String origin;

    @JsonProperty("DESTINATION")
    private String destination;
    
    @JsonProperty("TIME_DEPARTURE")
    private Timestamp departureTime;


    public Booked(String username, String flightNumber, String seatType, String seatNumber, int insuranceStatus, String origin, String destination, Timestamp time){
        this.username = username;
        this.flightNumber = flightNumber;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.insuranceStatus = insuranceStatus;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = time;
    }
    
    public Booked(){

    }
    public Booked(String name, String seatNumber, String seatType) {
        this.username = name;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
    }

    public String getUsername(){
        return username;
    }
    public String getNumber(){
        return flightNumber;
    }
    public String getSeatType(){
        return seatType;
    }
    public String getSeatNumber(){
        return seatNumber;
    }
    public int getInsurance(){
        return insuranceStatus;
    }

    public String getDestination(){
        return destination;
    }

    public String getOrigin(){
        return origin;
    }

    public Timestamp getDepartureTime(){
        return departureTime;
    }

    public void setDestination(String des){
        this.destination = des;
    }

    public void setOrigin(String or){

        this.origin = or;
    }

    public void setTimeDeparture(Timestamp time){
        this.departureTime = time;
    }
    
}

