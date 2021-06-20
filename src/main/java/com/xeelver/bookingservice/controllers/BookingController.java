package com.xeelver.bookingservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xeelver.bookingservice.services.FlightBookingService;
import com.xeelver.bookingservice.services.HotelBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class BookingController {

    private final HotelBookingService hotelBookingService;
    private final FlightBookingService flightBookingService;
    private final Gson gson;

    @GetMapping("/api/v1/booking/heatlthz")
    public String heathz() {
        return "Booking Service is Alive!!11";
    }


    @GetMapping(value = "/api/v1/booking/hotel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String> bookHotel(@RequestParam Map<String, String> body) {
        return new ResponseEntity<String>(hotelBookingService.createHotelBooking(body), HttpStatus.OK);
    }

    @PostMapping(value = "/api/v1/booking/flight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<JsonObject> bookFlight(@RequestBody JsonObject body)  {
        return new ResponseEntity<JsonObject>(flightBookingService.createFlightBooking(body), HttpStatus.OK);
    }

}
