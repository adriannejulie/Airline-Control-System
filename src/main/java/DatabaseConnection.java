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

    private Connection dbConnect;

    try{
        this.dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/TP480", "root", "password")
    } catch (SQLException e){
        e.printStackTrace();
    }
}

