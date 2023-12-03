package com.example.demo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin

@RequestMapping("/api/seats")
public class SeatsReservation {

    String jsonString = "[" +
        "{\"seatNumber\": \"1A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"1B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"1C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"1D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"2A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"2B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"2C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"2D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"3A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"3B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"3C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"3D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"4A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"4B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"4C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"4D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"5A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"5B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"5C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"5D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"6A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"6B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"6C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"6D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"7A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"7B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"7C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"7D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"8A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"8B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"8C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"8D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"9A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"9B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"9C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"9D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"10A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"10B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"10C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"10D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"11A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"11B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"11C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"11D\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"12A\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"12B\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"12C\", \"seatStatus\": \"Open\"}," +
        "{\"seatNumber\": \"12D\", \"seatStatus\": \"Open\"}" +
    "]";


// api/seats/reservation
@GetMapping("/reservation")
public ResponseEntity getReservedSeats(@RequestParam (value = "FLIGHT_NUMBER") String flightNUmber)
{
    DatabaseConnection databaseConnection = new DatabaseConnection();
    ArrayList<String> reservedSeats = databaseConnection.getReservedSeats(flightNUmber);
    //List<String> availableSeats = findAvailableSeats(jsonString, reservedSeats);

    if (reservedSeats != null) {
        return new ResponseEntity<>(reservedSeats, HttpStatus.CREATED);
    } else {
        // If the user already exists, return 208 (Already Reported)
        return new ResponseEntity<>("User already exists", HttpStatus.ALREADY_REPORTED);
    }
}

private static List<String> findAvailableSeats(String jsonString, List<String> existingSeats) {
    List<String> availableSeats = new ArrayList<>();

    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode seatArray = objectMapper.readTree(jsonString);

        for (JsonNode seatNode : seatArray) {
            String seatNumber = seatNode.get("seatNumber").asText();
            if (!existingSeats.contains(seatNumber)) {
                availableSeats.add(seatNumber);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions properly in a real application
    }

    return availableSeats;
}
}
