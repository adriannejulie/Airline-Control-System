package com.example.demo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Membership{

    long cardNumber;
    String email;

    private EmailService emailService = new EmailService();


    public Membership(){

    }
    
    public void sendEmail(Customers customer){
        email = customer.getEmail();
        System.out.println(email);
        cardNumber = getCreditCard();

        String email = customer.getEmail();
        String subject = "Air React Membership";
        String body = "Thank you signing up for our membership program!\n"
                       + "Here's some benefits you receive as a member: \n"
                       + "  Discounted access to out VIP lounges.\n"
                       + "  A member credit card: \n"
                       + "  Card Number: " + cardNumber
                       + " \n   1 Free Yearly Companion Ticket Code: psgr \n"
                       + "      Enter this code into the promo section when booking your next flight! "
                       + "\n    Monthly updates about deal and coupons for up to 30% off your next flight!." 
                       + "\n\n For your reference, your membership code: " + customer.getMembershipId()
                       + "\n If you have any questions about your membership, please contact our Help desk. \n Have a wonderful day!";


        emailService.sendEmail(email, subject, body);
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

    @Scheduled(cron = "0 0 0 1 * ?")
    public void sendMonthlyEmail() {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<String> emails = db.getMembershipEmails();
        emailService.sendMonthlyEmail(emails);
    }
}