package src.main.java;

import java.sql.Date;
import java.util.ArrayList;

public class Customers {
    private int idNo;
    private String username;
    private String password;
    private String name;
    private String birth;
    private String phoneNumber;
    private String cardNumber;
    private String email;
    private int membershipId = 0;
    private ArrayList<Booked> flights;

    public Customers(int idNo, String username, String password, String name, String birth, String phoneNumber, String cardNumber, String email, int membershipId){
        this.idNo = idNo;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.email = email;
        this.membershipId = membershipId;
    }

    public Customers(){
        this.idNo = 0;
        this.username = null;
        this.password = null;
        this.name = null;
        this.birth = null;
        this.phoneNumber = null;
        this.cardNumber = null;
        this.email = null;
        this.membershipId = 0;
    }

    public int getIdNo() {
        return idNo;
    }

    public void setIdNo(int idNo) {
        this.idNo = idNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public void createAccount(String username, String password, String name, Date birth, String phone, String card, String email, int membership){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.addUsers(username, password, name, birth, phone, card, email, membership);
    }

    public void BookFlight(Booked flight){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.addCustomerFlight(flight);
        //send email - bonus?

    }

    public void CancelFlight( Booked flight){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.removeCustomerFlight(flight);
    }

    public void ViewFlights(int id){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        this.flights = databaseConnection.viewBooked(id);
    }



}
