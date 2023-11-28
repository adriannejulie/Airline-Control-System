package src.main.java;

import java.sql.Date;

public class Flight{
    private int flightNumber;
    private String destination;
    private String origin;
    private Date timeDeparture;
    private int aircraftType;
    private int pilotId;
    private int flightAttendant1Id;
    private int flightAttendant2Id;
    private int flightAttendant3Id;
    private int flightAttendant4Id;
    private int business;
    private int comfort;
    private int ordinary;

    public Flight(int flightNumber, String origin, String destination, Date timeDeparture, int aircraftType, int pilotId, int flightAttendant1Id, int flightAttendant2Id, int flightAttendant3Id, int flightAttendant4Id) {
        business = 10;
        comfort = 15;
        ordinary = 55;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.timeDeparture = timeDeparture;
        this.aircraftType = aircraftType;
        this.pilotId = pilotId;
        this.flightAttendant1Id = flightAttendant1Id;
        this.flightAttendant2Id = flightAttendant2Id;
        this.flightAttendant3Id = flightAttendant3Id;
        this.flightAttendant4Id = flightAttendant4Id;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
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

    public Date getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(Date timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public int getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(int aircraftType) {
        this.aircraftType = aircraftType;
    }

    public int getPilotId() {
        return pilotId;
    }

    public void setPilotId(int pilotId) {
        this.pilotId = pilotId;
    }

    // Getter and setter for Flight Attendant 1 ID
    public int getFlightAttendant1Id() {
        return flightAttendant1Id;
    }

    public void setFlightAttendant1Id(int flightAttendant1Id) {
        this.flightAttendant1Id = flightAttendant1Id;
    }

    // Getter and setter for Flight Attendant 2 ID
    public int getFlightAttendant2Id() {
        return flightAttendant2Id;
    }

    public void setFlightAttendant2Id(int flightAttendant2Id) {
        this.flightAttendant2Id = flightAttendant2Id;
    }

    // Getter and setter for Flight Attendant 3 ID
    public int getFlightAttendant3Id() {
        return flightAttendant3Id;
    }

    public void setFlightAttendant3Id(int flightAttendant3Id) {
        this.flightAttendant3Id = flightAttendant3Id;
    }

    // Getter and setter for Flight Attendant 4 ID
    public int getFlightAttendant4Id() {
        return flightAttendant4Id;
    }

    public void setFlightAttendant4Id(int flightAttendant4Id) {
        this.flightAttendant4Id = flightAttendant4Id;
    }
}