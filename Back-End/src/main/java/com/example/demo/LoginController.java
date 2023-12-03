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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("/api/login")

public class LoginController {

    private Customers customer;
    public static void main(String[] args) {
        SpringApplication.run(FlightController.class, args);

    }

    @GetMapping("/customer")
    public ResponseEntity getCustomerLogin(@RequestParam(value = "USERNAME") String username) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Customers customer1 = databaseConnection.getCustomer(username);
        this.customer = customer1;
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/crew")
    public ResponseEntity getCrewLogin(@RequestParam(value = "USERNAME") String username) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Crew crew = databaseConnection.getCrew(username);
        if (crew != null) {
            return new ResponseEntity<>(crew, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 

    @GetMapping("/airlineAgent")
    public ResponseEntity getAgentLogin(@RequestParam(value = "USERNAME") String username) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
            AirlineAgent agent = databaseConnection.getAgent(username);
            if (agent != null) {
                return new ResponseEntity<>(agent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        
    }  
    


    

}
