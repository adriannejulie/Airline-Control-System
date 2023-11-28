package src.main.java;
import java.util.*;

public class Airline {
    private String name;
    private ArrayList<Crew> flightAttendants;
    private ArrayList<AirlineAgent> airlineAgents;
    private ArrayList<Crew> pilots;
    private ArrayList<SystemAdmin> systemAdmins;
    private ArrayList<Flight> flights;
    private ArrayList<Customers> customers;
    private ArrayList<Integer> aircraftIds;

    public Airline(DatabaseConnection db) {
        this.flightAttendants = db.getAllFlightAttendants();
        this.airlineAgents = db.getAllAirlineAgents();
        this.pilots = db.getAllPilots();
        this.systemAdmins = db.getAllAdminStaff();
        this.flights = db.getAllFlights();
        this.customers = db.getAllCustomers();
        this.aircraftIds = db.getAllAircraftIds();

        for (Flight flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Origin: " + flight.getOrigin());
            System.out.println("Destination: " + flight.getDestination());
            System.out.println("Time Departure: " + flight.getTimeDeparture());
            System.out.println("Aircraft Type: " + flight.getAircraftType());
            System.out.println("Pilot ID: " + flight.getPilotId());
            System.out.println("Flight Attendant IDs: " + flight.getFlightAttendant1Id() + ", " + flight.getFlightAttendant2Id() + ", " + flight.getFlightAttendant3Id() + ", " + flight.getFlightAttendant4Id());
            System.out.println("--------------------------------------");
        }

        for (Customers customer : customers) {
            System.out.println("Customer ID: " + customer.getIdNo());
            System.out.println("Username: " + customer.getUsername());
            System.out.println("Password: " + customer.getPassword());
            System.out.println("Name: " + customer.getName());
            System.out.println("Birth: " + customer.getBirth());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Card Number: " + customer.getCardNumber());
            System.out.println("Email: " + customer.getEmail());

            System.out.println("Membership ID: " + customer.getMembershipId());
            System.out.println("--------------------------------------");
        }

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
        }

        for (SystemAdmin systemAdmin : systemAdmins) {
            System.out.println("System Admin ID: " + systemAdmin.getIdNo());
            System.out.println("System Admin name: " + systemAdmin.getName());
            System.out.println("--------------------------------------");
        }

        for (int aircraftId : aircraftIds) {
            System.out.println("Aircraft ID: " + aircraftId);
            System.out.println("--------------------------------------");
        }
    }
}
