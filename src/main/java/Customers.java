package src.main.java;

public class Customers {
    private int idNo;
    private String username;
    private String password;
    private String name;
    private String birth;
    private String phoneNumber;
    private String cardNumber;
    private String email;
    private int flightNumber;
    private String seatType;
    private int seatNumber;
    private int membershipId;
    private int insuranceStatus;

    public Customers(int idNo, String username, String password, String name, String birth, String phoneNumber, String cardNumber, String email, int flightNumber, String seatType, int seatNumber, int membershipId, int insuranceStatus) {
        this.idNo = idNo;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.email = email;
        this.flightNumber = flightNumber;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.membershipId = membershipId;
        this.insuranceStatus = insuranceStatus;
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

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(int insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public void BookFlight(){}

    public void CancelFlight(){}

    public void ViewFlights(){}
}