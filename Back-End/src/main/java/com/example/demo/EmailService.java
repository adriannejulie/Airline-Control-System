package com.example.demo;

import java.util.ArrayList;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService() {
        this.javaMailSender = javaMailSender();
    }

    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // e.g., "smtp.gmail.com"
        mailSender.setPort(587); // Specify the port
        mailSender.setUsername("airreactairline@gmail.com"); // Your email address
        mailSender.setPassword("bcaz zqcp cies edrz"); // Your email password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
        System.out.println("Email Sent!");
    }

    public void sendMonthlyEmail(ArrayList<String> emails) {

        SimpleMailMessage message = new SimpleMailMessage();
        for (String item : emails) {
            message.setTo(item);
            message.setSubject("Monthly Promotions!");
            message.setText("Hooray! It's a new month, meaning it's another month to travel!\n" 
                            +"Come look at out website for some awesome deals on flights and memberships!");

            javaMailSender.send(message);
        }
        
    }
}

