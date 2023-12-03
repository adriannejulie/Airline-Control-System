package com.example.demo;

//connects to SQL database

import java.sql.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;


public class DatabaseConnection{ 
    private Statement stmt;
    private Connection dbConnection;
    private Membership member = new Membership();
    private Customers customer;
    private Flight flight;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/TP480";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    @Autowired
    private EmailService emailService;
    
    public DatabaseConnection() {
        try {
            this.dbConnection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            this.stmt = dbConnection.createStatement();
            System.out.println("Connection is not null: " + (dbConnection != null));

        } catch(SQLException ex) {
                ex.printStackTrace();
            }
    }

    public ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        String query = "SELECT FLIGHT_NUMBER, DESTINATION, ORIGIN, TIME_DEPARTURE, AIRCRAFT_TYPE FROM FLIGHT";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                String flight_id = resultSet.getString("FLIGHT_NUMBER");
                String destination = resultSet.getString("DESTINATION");
                String origin = resultSet.getString("ORIGIN");
                Timestamp date = resultSet.getTimestamp("TIME_DEPARTURE");
                int aircraftType = resultSet.getInt("AIRCRAFT_TYPE");

                Flight flight = new Flight(flight_id, destination, origin, date, aircraftType);

                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public ArrayList<Customers> getAllCustomers() {
        ArrayList<Customers> customers = new ArrayList<>();
        String query = "SELECT * FROM USERS" ;
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                // Retrieve customer data from the result set
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String name = resultSet.getString("NAME");
                Date birth = resultSet.getDate("BIRTH");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                String email = resultSet.getString("EMAIL");
                int membershipId = resultSet.getInt("MEMBERSHIP_ID");

                Customers customer = new Customers(
                         username, password, name, birth, phoneNumber, email,
                        membershipId
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return customers;
    }    

    public ArrayList<AirlineAgent> getAllAirlineAgents() {
        ArrayList<AirlineAgent> airlineAgents = new ArrayList<>();
        String query = "SELECT * FROM AIRLINE_AGENT";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int agentId = resultSet.getInt("USERNAME");
                String name = resultSet.getString("NAME");
                AirlineAgent airlineAgent = new AirlineAgent(agentId, name);
                airlineAgents.add(airlineAgent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airlineAgents;
    }

    public ArrayList<Crew> getAllPilots() {
        ArrayList<Crew> pilots = new ArrayList<>();
        String query = "SELECT * FROM CREW WHERE USERNAME BETWEEN 2000 AND 2999";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int crewId = resultSet.getInt("USERNAME");
                String name = resultSet.getString("NAME");
                Crew pilot = new Crew(crewId, name);
                pilots.add(pilot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return pilots;
    }
    
    public ArrayList<Crew> getAllFlightAttendants() {
        ArrayList<Crew> flightAttendants = new ArrayList<>();
        String query = "SELECT * FROM CREW WHERE USERNAME BETWEEN 3000 AND 3999";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int crewId = resultSet.getInt("USERNAME");
                String name = resultSet.getString("NAME");
                Crew flightAttendant = new Crew(crewId, name);
                flightAttendants.add(flightAttendant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return flightAttendants;
    }    

    public ArrayList<Integer> getAllAircraftIds() {
        ArrayList<Integer> aircraftIds = new ArrayList<>();
        String query = "SELECT AIRCRAFT_ID FROM AIRCRAFT";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int aircraftId = resultSet.getInt("AIRCRAFT_ID");
                aircraftIds.add(aircraftId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return aircraftIds;
    }    


    public ArrayList<String> getMembershipEmails() {
        ArrayList<String> emails = new ArrayList<>();
        String query = "SELECT EMAIL FROM USERS WHERE MEMBERSHIP_ID != 0";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                String email = resultSet.getString("EMAIL");
                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the error or throwing a custom exception.
        }
    
        return emails;
    }
    


    public Customers getCustomer(String user) {
        String query = "SELECT * FROM USERS WHERE USERNAME = ?";
        
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setString(1, user);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String userlogin = resultSet.getString("USERNAME");
                    String password = resultSet.getString("PASSWORD");
                    String name = resultSet.getString("NAME");
                    Date birth = resultSet.getDate("BIRTH");
                    String phoneNumber = resultSet.getString("PHONE_NUMBER");
                    String email = resultSet.getString("EMAIL");
                    int membershipId = resultSet.getInt("MEMBERSHIP_ID");
    
                    Customers customer = new Customers(
                            userlogin, password, name, birth, phoneNumber,
                            email, membershipId
                    );
                    this.customer = customer;
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the error or throwing a custom exception.
        }
        return null;
    }
    
    public Crew getCrew(String user) {
        String query = "SELECT * FROM CREW WHERE USERNAME = ?";
        
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setString(1, user);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userlogin = resultSet.getInt("USERNAME");
                    String password = resultSet.getString("NAME");
                    return new Crew(userlogin, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
    
        // No record found
        return null;
    }

    public AirlineAgent getAgent(String user) {
        String query = "SELECT * FROM AIRLINE_AGENT WHERE USERNAME = ?";
        
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setString(1, user);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userlogin = resultSet.getInt("USERNAME");
                    String password = resultSet.getString("NAME");
                    return new AirlineAgent(userlogin, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
    
        // No record found
        return null;
    }
    
    public boolean addUsers(Customers customer) {

        this.customer = customer;
        String query = "INSERT INTO USERS (USERNAME, PASSWORD, NAME, BIRTH, PHONE_NUMBER, EMAIL, MEMBERSHIP_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
    
        try {
            // Disable auto-commit to start a transaction explicitly
            dbConnection.setAutoCommit(false);
    
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
                // Set parameter values using setter methods
                preparedStatement.setString(1, customer.getUsername());
                preparedStatement.setString(2, customer.getPassword());
                preparedStatement.setString(3, customer.getName());
                preparedStatement.setDate(4, customer.getBirth());
                preparedStatement.setString(5, customer.getPhoneNumber());
                preparedStatement.setString(6, customer.getEmail());
                preparedStatement.setInt(7, customer.getMembershipId());
    
                // Execute the insert
                int rowsInserted = preparedStatement.executeUpdate();
    
                if (rowsInserted > 0) {
                    System.out.println("Insert successful!");
                    
                    // Commit the transaction
                    dbConnection.commit();
                    System.out.println(customer.getMembershipId());

                    if (customer.getMembershipId() != 0) {
                        try {
                            member.sendEmail(customer);
                        } catch (Exception e) {
                            System.out.println("Failed to send email: " + e.getMessage());
                        }
                    }
                    
                    return true;
                } else {
                    System.out.println("No rows were inserted.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception more gracefully
    
            // Rollback the transaction in case of an error
            try {
                if (dbConnection != null) {
                    dbConnection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // Enable auto-commit to return to the default behavior
                dbConnection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean addCustomerFlight(Booked flight) {
        String checkQuery = "SELECT * FROM BOOKED WHERE USERNAME = ? AND FLIGHT_NUMBER = ?";
        String insertQuery = "INSERT INTO BOOKED VALUES (?, ?, ?, ?, ?)";
       // String getFlight = "SELECT * FROM BOOKED WHERE FLIIGHT_NUMBER = " + flight.getNumber();

       //     try (ResultSet resultSet = stmt.executeQuery(getFlight)) {
       //         while (resultSet.next()) {
       //             // Retrieve customer data from the result set
       //             String destination = resultSet.getString("DESTINATION");
       //             String origin = resultSet.getString("ORIGIN");
       //             Timestamp date = resultSet.getTimestamp("TIME_DEPARTURE");
       //     

       //             flight.setDestination(destination);
       //             flight.setOrigin(origin);
       //             flight.setTimeDeparture(date);
       //         }
       //     } catch (SQLException e) {
       //         e.printStackTrace();
       //     }
    
        try (PreparedStatement checkStatement = dbConnection.prepareStatement(checkQuery)) {
            // Check if the combination of username and flight_number already exists
            checkStatement.setString(1, flight.getUsername());
            checkStatement.setString(2, flight.getNumber());
    
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (!resultSet.next()) {
                    // If the combination doesn't exist, proceed with the insertion
                    try (PreparedStatement insertStatement = dbConnection.prepareStatement(insertQuery)) {
                        insertStatement.setString(1, flight.getUsername());
                        insertStatement.setString(2, flight.getNumber());
                        insertStatement.setString(3, flight.getSeatType());
                        insertStatement.setString(4, flight.getSeatNumber());
                        insertStatement.setInt(5, flight.getInsurance());
    
                        insertStatement.executeUpdate();
                        return true;
                    }
                } else {
                    // Combination already exists, handle accordingly (e.g., throw an exception or log)
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
        return false;
    }
    

    public void removeCustomerFlight(String flight, String username){
        String query = "DELETE FROM BOOKED WHERE USERNAME = ? AND FLIGHT_NUMBER = ?";
    
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setString(1, username); // Corrected order
            preparedStatement.setString(2, flight);   // Corrected order
    
            int rowsAffected = preparedStatement.executeUpdate();
    
            // Optionally, check the number of rows affected
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) deleted successfully.");
            } else {
                System.out.println("No rows deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
    }
    

    public void addMembership(int id){
        String strUpdate = "UPDATE USER SET MEMBERSHP_ID = (SELECT MAX(MEMBERSHIP_ID) + 1 FROM USERS) WHERE ID_NO = " + id;
         System.out.println("The SQL statement is: " + strUpdate + "\n");  // Echo for debugging
         try {
            stmt.executeUpdate(strUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMembership (String username){
        String strUpdate = "UPDATE USER SET MEMBERSHP_ID = 0000 WHERE USERNAME = " + username;
        System.out.println("The SQL statement is: " + strUpdate + "\n");  // Echo for debugging
        try {
           stmt.executeUpdate(strUpdate);
       } catch (SQLException e) {
           e.printStackTrace();
       } 
    }

    public void changePaymentInfo(String username, String cardNumber){
        String strUpdate = "UPDATE USER SET CARD_NUMBER =" + cardNumber + " WHERE USERNAME = " + username;
        System.out.println("The SQL statement is: " + strUpdate + "\n");  // Echo for debugging
        try {
           stmt.executeUpdate(strUpdate);
       } catch (SQLException e) {
           e.printStackTrace();
       } 
    }

    public ArrayList<Booked> viewBooked(String username) {
        ArrayList<Booked> bookings = new ArrayList<>();
        String query = "SELECT * FROM BOOKED WHERE USERNAME = ?";
    
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String user = resultSet.getString("USERNAME");
                    String flightNumber = resultSet.getString("FLIGHT_NUMBER");
                    String seatType = resultSet.getString("SEAT_TYPE");
                    String seatNumber = resultSet.getString("SEAT_NUMBER");
                    int insuranceStatus = resultSet.getInt("INSURANCE_STATUS");
    
                    String queryFlight = "SELECT * FROM FLIGHT WHERE FLIGHT_NUMBER = ?";
    
                    try (PreparedStatement prepared = dbConnection.prepareStatement(queryFlight)) {
                        // Use `prepared` instead of `preparedStatement`
                        prepared.setString(1, flightNumber);
    
                        try (ResultSet result = prepared.executeQuery()) {
                            while (result.next()) {
                                String origin = result.getString("ORIGIN");  // Use `result` instead of `resultSet`
                                String destination = result.getString("DESTINATION");
                                Timestamp time = result.getTimestamp("TIME_DEPARTURE");
    
                                Booked booking = new Booked(user, flightNumber, seatType, seatNumber, insuranceStatus, origin, destination, time);
                                bookings.add(booking);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Handle the exception according to your application's needs
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's needs
        }
    
        return bookings;
    }
    
    

    public ArrayList<Booked> viewPassengers(String flightNumber){
        // Assuming dbConnection is your database connection object
        ArrayList<Booked> passengers = new ArrayList<>();
        String query = "SELECT U.NAME, B.SEAT_NUMBER, B.SEAT_TYPE " +
        "FROM USERS U " +
        "JOIN BOOKED B ON U.USERNAME = B.USERNAME " +
        "WHERE B.FLIGHT_NUMBER = ?";


        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setString(1, flightNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    String seatType = resultSet.getString("SEAT_TYPE");
                    String seatNumber = resultSet.getString("SEAT_NUMBER");

                    // Process the retrieved data as needed
                    System.out.println("Name: " + name + ", Seat Number: " + seatNumber);
                    Booked passenger = new Booked(name, seatNumber, seatType);

                    passengers.add(passenger);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;

    }

    public Flight getFlightOnly(String flightNumber) {
        String query = "SELECT FLIGHT_NUMBER, DESTINATION, ORIGIN, TIME_DEPARTURE, AIRCRAFT_TYPE FROM FLIGHT WHERE FLIGHT_NUMBER = ?;";
    
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            // Set the flightNumber as a parameter
            preparedStatement.setString(1, flightNumber);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Retrieve flight data from the result set
                    String flight_id = resultSet.getString("FLIGHT_NUMBER");
                    String destination = resultSet.getString("DESTINATION");
                    String origin = resultSet.getString("ORIGIN");
                    Timestamp date = resultSet.getTimestamp("TIME_DEPARTURE");
                    int aircraftType = resultSet.getInt("AIRCRAFT_TYPE");
    
                    Flight flight = new Flight(
                            flight_id, destination, origin, date, aircraftType
                    );
                    return flight;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Crew> getFlightCrew(String flightNumber) {
        ArrayList<Crew> crewList = new ArrayList<>();
        String query = "SELECT CREW.* FROM CREW " +
                "WHERE CREW.USERNAME IN (" +
                "    SELECT PILOT1 FROM FLIGHT WHERE FLIGHT_NUMBER = ? " +
                "    UNION " +
                "    SELECT FLIGHT_ATTENDANT1 FROM FLIGHT WHERE FLIGHT_NUMBER = ? " +
                "    UNION " +
                "    SELECT FLIGHT_ATTENDANT2 FROM FLIGHT WHERE FLIGHT_NUMBER = ? " +
                "    UNION " +
                "    SELECT FLIGHT_ATTENDANT3 FROM FLIGHT WHERE FLIGHT_NUMBER = ? " +
                "    UNION " +
                "    SELECT FLIGHT_ATTENDANT4 FROM FLIGHT WHERE FLIGHT_NUMBER = ?" +
                ")";
    
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            for (int i = 1; i <= 5; i++) {
                preparedStatement.setString(i, flightNumber);
            }
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int username = resultSet.getInt("USERNAME");
                    String name = resultSet.getString("NAME");
    
                    Crew crew = new Crew(username, name);
                    crewList.add(crew);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return crewList;
    }
    
    
    

    public Flight getFlight(String flightNumber) {
        String query = "SELECT * FROM FLIGHT WHERE FLIGHT_NUMBER = ?";
        
        try (PreparedStatement pstmt = dbConnection.prepareStatement(query)) {
            pstmt.setString(1, flightNumber);
    
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    String flight_id = resultSet.getString("FLIGHT_NUMBER");
                    String destination = resultSet.getString("DESTINATION");
                    String origin = resultSet.getString("ORIGIN");
                    Timestamp date = resultSet.getTimestamp("TIME_DEPARTURE");
                    int aircraftType = resultSet.getInt("AIRCRAFT_TYPE");
                    int pilot = resultSet.getInt("PILOT1");
                    int flightAttendant1 = resultSet.getInt("FLIGHT_ATTENDANT1");
                    int flightAttendant2 = resultSet.getInt("FLIGHT_ATTENDANT2");
                    int flightAttendant3 = resultSet.getInt("FLIGHT_ATTENDANT3");
                    int flightAttendant4 = resultSet.getInt("FLIGHT_ATTENDANT4");
    
                    Flight flight = new Flight(
                            flight_id, destination, origin, date, aircraftType, pilot,
                            flightAttendant1, flightAttendant2,
                            flightAttendant3, flightAttendant4
                    );
                    return flight;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the error or throwing a custom exception.
        }
        return null;
    }
    

    public ArrayList<Flight> getCFlights(String user) {
        ArrayList<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM FLIGHT WHERE ? IN (FLIGHT_ATTENDANT1, FLIGHT_ATTENDANT2, FLIGHT_ATTENDANT3, FLIGHT_ATTENDANT4, PILOT1)";
        
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
            preparedStatement.setString(1, user);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String flight_id = resultSet.getString("FLIGHT_NUMBER");
                    String destination = resultSet.getString("DESTINATION");
                    String origin = resultSet.getString("ORIGIN");
                    Timestamp date = resultSet.getTimestamp("TIME_DEPARTURE");
                    int aircraftType = resultSet.getInt("AIRCRAFT_TYPE");
    
                    Flight flight = new Flight(flight_id, destination, origin, date, aircraftType);
                    flights.add(flight);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
        return flights;
    }

        //Returns an ArrayList of Reserved Seats
        public ArrayList<String> getReservedSeats(String flightNumber) {
            ArrayList<String> reservedSeats = new ArrayList<>();
            String query = "SELECT * FROM BOOKED WHERE FLIGHT_NUMBER = ?";
            
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) {
                preparedStatement.setString(1, flightNumber);
        
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String seat = resultSet.getString("SEAT_NUMBER");
                        reservedSeats.add(seat);
                    }
                    return reservedSeats;
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception according to your application's needs
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
            }
            return null;
        }
        



    

    
      
    /* System Admin functions
    public void removeCrew(int username){
        String query = "DELETE FROM CREW WHERE USERNAME = " + username;
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    public void removeCustomer(String username) {
        String query = "DELETE FROM USERS WHERE USERNAME = " + username;
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  

    public void removeAircraft(int aircraftId) {
        String query = "DELETE FROM AIRCRAFT WHERE AIRCRAFT_ID = " + aircraftId;
        System.out.println("The SQL statement is: " + query + "\n");
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlight(int flightNumber) {
        String query = "DELETE FROM FLIGHT WHERE FLIGHT_NUMBER = " + flightNumber;
        System.out.println("The SQL statement is: " + query + "\n");
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCrew(int id, String name){
        String query = "INSERT INTO CREW VALUES (" + id + ", '" + name + "')";
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAircraft(int aircraftId) {
        String query = "INSERT INTO AIRCRAFT VALUES (" + aircraftId + ")";
        System.out.println("The SQL statement is: " + query + "\n");
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFlight(int flightNumber, String origin, String destination, Date timeDeparture, int aircraftType, int pilot1, int flightAttendant1, int flightAttendant2, int flightAttendant3, int flightAttendant4) {
        String query = "INSERT INTO FLIGHT VALUES (" + flightNumber + ", '" + origin + "', '" + destination + "', '"
                + timeDeparture + "', " + aircraftType + ", " + pilot1 + ", " + flightAttendant1 + ", " + flightAttendant2
                + ", " + flightAttendant3 + ", " + flightAttendant4 + ")";
        System.out.println("The SQL statement is: " + query + "\n");
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */

    //Flights without crew


}


