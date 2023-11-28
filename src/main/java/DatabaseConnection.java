//connects to SQL database
package src.main.java;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseConnection{ 
    private Statement stmt;
    private Connection dbConnection;
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/TP480";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    
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
        String query = "SELECT * FROM FLIGHT";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int flightNumber = resultSet.getInt("FLIGHT_NUMBER");
                String origin = resultSet.getString("ORIGIN");
                String destination = resultSet.getString("DESTINATION");
                Date timeDeparture = resultSet.getDate("TIME_DEPARTURE");
                int aircraftType = resultSet.getInt("AIRCRAFT_TYPE");
                int pilotId = resultSet.getInt("PILOT1");
                int flightAttendant1Id = resultSet.getInt("FLIGHT_ATTENDANT1");
                int flightAttendant2Id = resultSet.getInt("FLIGHT_ATTENDANT2");
                int flightAttendant3Id = resultSet.getInt("FLIGHT_ATTENDANT3");
                int flightAttendant4Id = resultSet.getInt("FLIGHT_ATTENDANT4");

                Flight flight = new Flight(flightNumber, origin, destination, timeDeparture, aircraftType, pilotId, flightAttendant1Id, flightAttendant2Id, flightAttendant3Id, flightAttendant4Id);

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
                int idNo = resultSet.getInt("ID_NO");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String name = resultSet.getString("NAME");
                String birth = resultSet.getString("BIRTH");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                String cardNumber = resultSet.getString("CARD_NUMBER");
                String email = resultSet.getString("EMAIL");
                int membershipId = resultSet.getInt("MEMBERSHIP_ID");

                Customers customer = new Customers(
                        idNo, username, password, name, birth, phoneNumber,
                        cardNumber, email,
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
                int agentId = resultSet.getInt("AGENT_ID");
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
        String query = "SELECT * FROM CREW WHERE CREW_ID BETWEEN 2000 AND 2999";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int crewId = resultSet.getInt("CREW_ID");
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
        String query = "SELECT * FROM CREW WHERE CREW_ID BETWEEN 3000 AND 3999";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int crewId = resultSet.getInt("CREW_ID");
                String name = resultSet.getString("NAME");
                Crew flightAttendant = new Crew(crewId, name);
                flightAttendants.add(flightAttendant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return flightAttendants;
    }    

    public ArrayList<SystemAdmin> getAllAdminStaff() {
        ArrayList<SystemAdmin> adminStaff = new ArrayList<>();
        String query = "SELECT * FROM ADMIN_STAFF";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int adminId = resultSet.getInt("ADMIN_ID");
                String name = resultSet.getString("NAME");
                SystemAdmin admin = new SystemAdmin(adminId, name);
                adminStaff.add(admin);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminStaff;
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

    public Customers getCustomer(String user){
        String query = "SELECT * FROM USERS WHERE USERNAME = " + user;
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                // Retrieve customer data from the result set
                int idNo = resultSet.getInt("ID_NO");
                String userlogin = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String name = resultSet.getString("NAME");
                String birth = resultSet.getString("BIRTH");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                String cardNumber = resultSet.getString("CARD_NUMBER");
                String email = resultSet.getString("EMAIL");
                int membershipId = resultSet.getInt("MEMBERSHIP_ID");

                Customers customer = new Customers(
                        idNo, userlogin, password, name, birth, phoneNumber,
                        cardNumber, email,
                        membershipId
                );
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Customers();
    }

    public void changePaymentInfo(int id, String cardNumber){
        String strUpdate = "UPDATE USER SET CARD_NUMBER =" + cardNumber + " WHERE ID_NO = " + id;
        System.out.println("The SQL statement is: " + strUpdate + "\n");  // Echo for debugging
        try {
           stmt.executeUpdate(strUpdate);
       } catch (SQLException e) {
           e.printStackTrace();
       } 
    }

    public ArrayList<Booked> viewBooked(int id){
        ArrayList<Booked> bookings = new ArrayList<>();

        String query = "SELECT * FROM BOOKED WHERE USER_ID = " + id;
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                // Retrieve customer data from the result set
                int idNo = resultSet.getInt("USER_NO");
                int flightNumber = resultSet.getInt("FLIGHT_NUMBER");
                String seatType = resultSet.getString("SEAT_TYPE");
                int seatNumber = resultSet.getInt("SEAT_NUMBER");
                int insuranceStatus = resultSet.getInt("INSURANCE_STATUS");
       
                Booked booking = new Booked(
                        idNo, flightNumber, seatType, seatNumber, insuranceStatus
                );
                bookings.add(booking);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public ArrayList<Customers> viewPassengers(int flightNumber){
        ArrayList<Customers> passengers = new ArrayList<>();

        String query = "SELECT * FROM USERS WHERE FLIGHT_NUMBER = " + flightNumber;
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                // Retrieve customer data from the result set
                int idNo = resultSet.getInt("ID_NO");
                String userlogin = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String name = resultSet.getString("NAME");
                String birth = resultSet.getString("BIRTH");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                String cardNumber = resultSet.getString("CARD_NUMBER");
                String email = resultSet.getString("EMAIL");
                int membershipId = resultSet.getInt("MEMBERSHIP_ID");

                Customers customer = new Customers(
                        idNo, userlogin, password, name, birth, phoneNumber,
                        cardNumber, email,
                        membershipId
                );
                passengers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public void addUsers(String username, String password, String name, Date birth, String phone, String card, String email, int membership){
        String query = "INSERT INTO USERS VALUES ((SELECT MAX(ID_NO) + 1 FROM USERS)" + "," + username + "," + password + "," + name + "," + birth + "," + phone + "," + card + "," + email + "," + membership + ")";
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCustomer(int id) {
        String query = "DELETE FROM USERS WHERE ID_NO = " + id;
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
    
    public void addCustomerFlight(Booked flight){
        String query = "INSERT INTO BOOKED VALUES (" + flight.getID()+ ", " + flight.getNumber() + ", " + flight.getSeatType() + ", " + flight.getSeatNumber() + "," + flight.getInsurance() + ")";
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCustomerFlight(Booked flight){
        String query = "DELETE FROM BOOKED VALUES (" + flight.getID()+ ", " + flight.getNumber() + ", " + flight.getSeatType() + ", " + flight.getSeatNumber() + "," + flight.getInsurance() + ")";
        System.out.println("The SQL statement is: " + query + "\n");  // Echo for debugging
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void removeMembership (int id){
        String strUpdate = "UPDATE USER SET MEMBERSHP_ID = 0000 WHERE ID_NO = " + id;
        System.out.println("The SQL statement is: " + strUpdate + "\n");  // Echo for debugging
        try {
           stmt.executeUpdate(strUpdate);
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

    public void removeCrew(int id){
        String query = "DELETE FROM CREW WHERE CREW_ID = " + id;
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

    public void removeAircraft(int aircraftId) {
        String query = "DELETE FROM AIRCRAFT WHERE AIRCRAFT_ID = " + aircraftId;
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

    public void deleteFlight(int flightNumber) {
        String query = "DELETE FROM FLIGHT WHERE FLIGHT_NUMBER = " + flightNumber;
        System.out.println("The SQL statement is: " + query + "\n");
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

