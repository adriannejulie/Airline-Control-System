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

    public void queryString() {
        String strSelect = "SELECT ID_NO FROM USERS";
        System.out.println("The SQL query: " + strSelect + "\n");
    
        try (ResultSet rset = stmt.executeQuery(strSelect)) {
            System.out.println("The records selected are:");
            int rowCount = 0;
    
            while (rset.next()) {
                String idNumber = rset.getString("ID_NO");
                System.out.println(idNumber);
                ++rowCount;
            }
    
            System.out.println("Total number of records = " + rowCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM FLIGHT";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int flightNumber = resultSet.getInt("FLIGHT_NUMBER");
                String destination = resultSet.getString("DESTINATION");
                String origin = resultSet.getString("ORIGIN");
                Date timeDeparture = resultSet.getDate("TIME_DEPARTURE");
                int aircraftType = resultSet.getInt("AIRCRAFT_TYPE");

                Flight flight = new Flight(flightNumber, destination, origin, timeDeparture, aircraftType);

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
        String query = "SELECT AGENT_ID FROM AIRLINE_AGENT";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int agentId = resultSet.getInt("AGENT_ID");
                AirlineAgent airlineAgent = new AirlineAgent(agentId);
                airlineAgents.add(airlineAgent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airlineAgents;
    }

    public ArrayList<Pilot> getAllPilots() {
        ArrayList<Pilot> pilots = new ArrayList<>();
        String query = "SELECT CREW_ID FROM CREW WHERE CREW_ID BETWEEN 2000 AND 2999";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int crewId = resultSet.getInt("CREW_ID");
                Pilot pilot = new Pilot(crewId);
                pilots.add(pilot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return pilots;
    }
    
    public ArrayList<FlightAttendant> getAllFlightAttendants() {
        ArrayList<FlightAttendant> flightAttendants = new ArrayList<>();
        String query = "SELECT CREW_ID FROM CREW WHERE CREW_ID BETWEEN 3000 AND 3999";
    
        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int crewId = resultSet.getInt("CREW_ID");
                FlightAttendant flightAttendant = new FlightAttendant(crewId);
                flightAttendants.add(flightAttendant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return flightAttendants;
    }    

    public ArrayList<SystemAdmin> getAllAdminStaff() {
        ArrayList<SystemAdmin> adminStaff = new ArrayList<>();
        String query = "SELECT ADMIN_ID FROM ADMIN_STAFF";

        try (ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                int adminId = resultSet.getInt("ADMIN_ID");
                SystemAdmin admin = new SystemAdmin(adminId);
                adminStaff.add(admin);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminStaff;
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

    public void addUsers(String username, String password, String name, Date birth, String phone, String card, String email, int membership){
        String query = "INSERT INTO USERS VALUES ((SELECT MAX(ID_NO) + 1 FROM USERS)" + "," + username + "," + password + "," + name + "," + birth + "," + phone + "," + card + "," + email + "," + membership + ")";
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
      

    public void removeCrew(int id){

    }

    public void removeCustomer(int id){

    }

    public void removeAircraft(int id){

    }

    public void deleteFlight(int id){

    }

    public void addCrew(int id){

    }

    public void addAircraft(int id){

    }

    public void addFlight(int id){
        
    }




}

