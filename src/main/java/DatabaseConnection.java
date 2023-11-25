//connects to SQL database
package src.main.java;

import java.sql.*;

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
    
}

