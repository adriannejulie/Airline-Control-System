package src.main.java;

import java.sql.Date;
import java.util.Random;

public class Membership{

    long cardNumber;
    public void sendEmail(Flight flight){
        int flightNumber = flight.getFlightNumber();
        String destination = flight.getDestination();
        String origin = flight.getOrigin();
        Date timeDeparture = flight.getTimeDeparture();
        int aircraftType = flight.getAircraftType();

        //send email? --bonus

    }

    public long getCreditCard(){

        Random random = new Random();
        // Use StringBuilder to avoid precision issues with long
        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10); // Generate a random digit (0-9)
            sb.append(digit);
        }
        this.cardNumber = Long.parseLong(sb.toString());
        return cardNumber;
    }
}