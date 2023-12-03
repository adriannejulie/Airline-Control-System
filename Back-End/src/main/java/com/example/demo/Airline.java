package com.example.demo;

import java.util.*;

public class Airline {
    private String name;
    private ArrayList<Crew> flightAttendants;
    private ArrayList<AirlineAgent> airlineAgents;
    private ArrayList<Crew> pilots;
    private ArrayList<Flight> flights;
    private ArrayList<Customers> customers;

    public Airline(DatabaseConnection db) {
        this.flightAttendants = db.getAllFlightAttendants();
        this.airlineAgents = db.getAllAirlineAgents();
        this.pilots = db.getAllPilots();
        this.flights = db.getAllFlights();
        this.customers = db.getAllCustomers();

        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Origin: " + flight.getOrigin());
            System.out.println("Time Departure: " + flight.getTimeDeparture());
            System.out.println("Aircraft Type: " + flight.getAircraftType());
            System.out.println("--------------------------------------");
        }

        for (Customers customer : customers) {
            System.out.println("Username: " + customer.getUsername());
            System.out.println("Password: " + customer.getPassword());
            System.out.println("Name: " + customer.getName());
            System.out.println("Birth: " + customer.getBirth());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Email: " + customer.getEmail());

            System.out.println("Membership ID: " + customer.getMembershipId());
            System.out.println("--------------------------------------");
        }
        /*
        for (Crew flightAttendant : flightAttendants) {
            System.out.println("Flight Attendant ID: " + flightAttendant.getIdNo());
            System.out.println("Flight Attendant name: " + flightAttendant.getName());
            System.out.println("--------------------------------------");
        }

        for (AirlineAgent airlineAgent : airlineAgents) {
            System.out.println("Airline Agent ID: " + airlineAgent.getIdNo());
            System.out.println("Airline Agent name: " + airlineAgent.getName());
            System.out.println("--------------------------------------");
        }

        for (Crew pilot : pilots) {
            System.out.println("Pilot ID: " + pilot.getIdNo());
            System.out.println("Pilot name: " + pilot.getName());
            System.out.println("--------------------------------------");
        }*/
    }
}
