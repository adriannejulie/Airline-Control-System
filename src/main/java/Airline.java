package src.main.java;
import java.util.*;

public class Airline{
    private String name;
    private ArrayList<FlightAttendant> flightAttendants;
    private ArrayList<AirlineAgent> airlineAgents;
    private Pilot pilot;
    private BookingSystem bookingSystem;
    private Planes planes;
    private ArrayList<Flight> flights;

    public Airline(DatabaseConnection db){
        ArrayList<Flight> flights = db.getAllFlights();
        this.flights = flights;

        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Origin: " + flight.getOrigin());
            System.out.println("Time Departure: " + flight.getTimeDeparture());
            System.out.println("Aircraft Type: " + flight.getAircraftType());
            System.out.println("--------------------------------------");
        }
    }
}