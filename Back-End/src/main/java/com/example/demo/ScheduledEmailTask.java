package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEmailTask {

    @Autowired
    private EmailService emailService;

    // Run the task at midnight on the first day of each month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void sendMonthlyEmail(ArrayList<String> emails) {
        emailService.sendMonthlyEmail(emails);
    }
}

