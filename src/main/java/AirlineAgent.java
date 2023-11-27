package src.main.java;

import java.util.ArrayList;

public class AirlineAgent extends Employee{
    Booked book;
    Flight flight;
    public AirlineAgent(int idNo) {        
        super(idNo);
    }

    public void createBooking(Booked booking){
        this.book = booking;
    }

    public void BookFlight(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.addCustomerFlight(book);
    }

    public ArrayList<Customers> ViewAllPassengers(DatabaseConnection db){
        ArrayList<Customers> passengers = new ArrayList<>();
        passengers = db.viewPassengers(flight.getFlightNumber());
        return passengers;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public ArrayList<Flight> getFlights(DatabaseConnection db){
        ArrayList<Flight> flights = new ArrayList<>();
        flights = db.getAllFlights();
        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Origin: " + flight.getOrigin());
            System.out.println("Time Departure: " + flight.getTimeDeparture());
            System.out.println("Aircraft Type: " + flight.getAircraftType());
            System.out.println("--------------------------------------");
        }
        return flights;
    }
}