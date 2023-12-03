package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
@CrossOrigin

@RequestMapping("/api/flightData")

public class FlightController {
    public static void main(String[] args) {
        SpringApplication.run(FlightController.class, args);

    }

    @GetMapping("/logistics")
    public ResponseEntity getFlightData(@RequestParam(value = "FLIGHT_NUMBER") String flightNumber){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Flight flights = databaseConnection.getFlightOnly(flightNumber);
        if (flights != null) {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/flightCrew")
    public ResponseEntity getFlightCrewOnly(@RequestParam(value = "FLIGHT_NUMBER") String flightNumber){
        ArrayList<Crew> allCrewMembers = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        allCrewMembers = databaseConnection.getFlightCrew(flightNumber);
        if (allCrewMembers != null) {
            return new ResponseEntity<>(allCrewMembers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/flight")
    public ResponseEntity getFlight(@RequestParam(value = "FLIGHT_NUMBER") String flightNumber){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Flight flights = databaseConnection.getFlight(flightNumber);
        if (flights != null) {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allData")
    public ResponseEntity getAllFlights(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ArrayList<Flight> flights = new ArrayList<>();
        flights = databaseConnection.getAllFlights();
        if (flights != null) {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @GetMapping("/passengers")
    public ResponseEntity getPassengers(@RequestParam(value = "FLIGHT_NUMBER") String flightnumber) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ArrayList<Booked> passengers = new ArrayList<>();
        passengers = databaseConnection.viewPassengers(flightnumber);
        if (passengers != null) {
            return new ResponseEntity<>(passengers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}

//Flight without Crew
//Flight with crew
//Flight with only crew

