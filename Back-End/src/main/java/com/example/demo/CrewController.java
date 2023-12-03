package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("/api/crew")

public class CrewController {
    public static void main(String[] args) {
        SpringApplication.run(CrewController.class, args);

    }

    @GetMapping("/scheduledFlights")
    public ResponseEntity getCrewFlights(@RequestParam(value = "USERNAME") String username) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ArrayList<Flight> flights = new ArrayList<>();
        flights = databaseConnection.getCFlights(username);
        if (flights != null) {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    
    
    //get functions for all the passengers on each flight - but only their names seatNumber
}
