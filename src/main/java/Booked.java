package src.main.java;

public class Booked {

    private int id;
    private int flightNumber;
    private String seatType;
    private int seatNumber;
    private int insuranceStatus;

    public Booked(int id, int flightNumber, String seatType, int seatNumber, int insuranceStatus){
        this.id = id;
        this.flightNumber = flightNumber;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.insuranceStatus = insuranceStatus;
    }
    
    public int  getID(){
        return id;
    }
    public int getNumber(){
        return flightNumber;
    }
    public String getSeatType(){
        return seatType;
    }
    public int getSeatNumber(){
        return seatNumber;
    }
    public int getInsurance(){
        return insuranceStatus;
    }
    
}
