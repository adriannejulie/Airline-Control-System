package src.main.java;

import java.sql.Date;

public class Flight{
    private int flightNumber;
    private String destination;
    private String origin;
    private Date timeDeparture;
    private int aircraftType;

    public Flight(int flightNumber, String destination, String origin, Date timeDeparture, int aircraftType) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.origin = origin;
        this.timeDeparture = timeDeparture;
        this.aircraftType = aircraftType;
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
}