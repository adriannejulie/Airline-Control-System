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
    public static void main(String[] args) {
    try{
        Connection dbConnect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/TP480", "root", "password");
        Statement stmt = dbConnect.createStatement();

        String strSelect = "SELECT ID_NO FROM USERS";
        System.out.println("The SQL query: " + strSelect + "\n");

        ResultSet rset = stmt.executeQuery(strSelect);
 
         // Step 4: Process the 'ResultSet' by scrolling the cursor forward via next().
         //  For each row, retrieve the contents of the cells with getXxx(columnName).
        System.out.println("The records selected are:");
        int rowCount = 0;
         // Row-cursor initially positioned before the first row of the 'ResultSet'.
         // rset.next() inside the whole-loop repeatedly moves the cursor to the next row.
         // It returns false if no more rows.
        while(rset.next()) {   // Repeatedly process each row
            String ID_NO = rset.getString("ID_NO");  // retrieve a 'String'-cell in the row
            
            System.out.println(ID_NO);
            ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
    } catch (SQLException e){
        e.printStackTrace();
    }
}
}

