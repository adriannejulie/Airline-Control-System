package com.example.demo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Flight{

    @JsonProperty("FLIGHT_NUMBER")
    private String flightNumber;

    @JsonProperty("DESTINATION")
    private String destination;

    @JsonProperty("ORIGIN")
    private String origin;

    @JsonProperty("TIME_DEPARTURE")
    private Timestamp timeDeparture;

    @JsonProperty("AIRCRAFT_TYPE")
    private int aircraftType;

    @JsonProperty("PILOT1")
    private int pilot;

    @JsonProperty("FLIGHT_ATTENDANT1")
    private int flightAttendant1;

    @JsonProperty("FLIGHT_ATTENDANT2")
    private int flightAttendant2;

    @JsonProperty("FLIGHT_ATTENDANT3")
    private int flightAttendant3;

    @JsonProperty("FLIGHT_ATTENDANT4")
    private int flightAttendant4;


    public Flight(String flightNumber, String destination, String origin, Timestamp timeDeparture, int aircraftType, int pilot, int flightAttendant1, int flightAttendant2, int flightAttendant3, int flightAttendant4) {

        this.flightNumber = flightNumber;
        this.destination = destination;
        this.origin = origin;
        this.timeDeparture = timeDeparture;
        this.aircraftType = aircraftType;
        this.pilot = pilot;
        this.flightAttendant1 = flightAttendant1;
        this.flightAttendant2 = flightAttendant2;
        this.flightAttendant3 = flightAttendant3;
        this.flightAttendant4 = flightAttendant4;

    }

    public Flight(){
        
    }

    public Flight(String flightNumber, String destination, String origin, Timestamp timeDeparture, int aircraftType) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.origin = origin;
        this.timeDeparture = timeDeparture;
        this.aircraftType = aircraftType;
    }

    public Flight(int pilot, int flightAttendant1, int flightAttendant2, int flightAttendant3, int flightAttendant4) {
        this.pilot = pilot;
        this.flightAttendant1 = flightAttendant1;
        this.flightAttendant2 = flightAttendant2;
        this.flightAttendant3 = flightAttendant3;
        this.flightAttendant4 = flightAttendant4;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Timestamp getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Timestamp timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public int getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(int aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setPilot(int pilot_id){
        this.pilot = pilot_id;
    }

    public void setFlightAttendant1(int id){
        this.flightAttendant1 = id;
    }

    public void setFlightAttendant2(int id){
        this.flightAttendant2 = id;
    }

    public void setFlightAttendant3(int id){
        this.flightAttendant3 = id;
    }

    public void setFlightAttendant4(int id){
        this.flightAttendant4 = id;
    }

    public int getPilot(){
        return pilot;
    }

    public int getFlightAttendant1(){
        return flightAttendant1;
    }

    public int getFlightAttendant2(){
        return flightAttendant2;
    }

    public int getFlightAttendant3(){
        return flightAttendant3;
    }

    public int getFlightAttendant4(){
        return flightAttendant4;
    }

    public ArrayList<Integer> getAllFlightAttendants(){
        ArrayList<Integer> flightAttendants = new ArrayList<>();
        flightAttendants.add(flightAttendant1);
        flightAttendants.add(flightAttendant2);
        flightAttendants.add(flightAttendant3);
        flightAttendants.add(flightAttendant4);

        return flightAttendants;
    }
}
