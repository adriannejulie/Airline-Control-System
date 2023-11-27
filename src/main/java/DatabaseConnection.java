//connects to SQL database
package src.main.java;

import java.sql.*;
import java.util.ArrayList;

/**
Exports:
- All Flight Data
- All Employee Data
- All Customer Data
- All Plane Data
- All Airport Data

- Edit/Delete Versions of above
 */


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
                String timeDeparture = resultSet.getString("TIME_DEPARTURE");
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
        String query = "SELECT * FROM USERS";
    
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
                int flightNumber = resultSet.getInt("FLIGHT_NUMBER");
                String seatType = resultSet.getString("SEAT_TYPE");
                int seatNumber = resultSet.getInt("SEAT_NUMBER");
                int membershipId = resultSet.getInt("MEMBERSHIP_ID");
                int insuranceStatus = resultSet.getInt("INSURANCE_STATUS");

                Customers customer = new Customers(
                        idNo, username, password, name, birth, phoneNumber,
                        cardNumber, email, flightNumber, seatType, seatNumber,
                        membershipId, insuranceStatus
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
    
}

