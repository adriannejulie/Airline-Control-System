package src.main.java;
import java.sql.*;

class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Airline airline = new Airline(databaseConnection);
    }
}
