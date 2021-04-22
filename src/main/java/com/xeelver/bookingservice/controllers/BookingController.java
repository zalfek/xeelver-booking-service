package com.xeelver.bookingservice.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @GetMapping("/api/secured")
    public String secured(){
        return "authenticated";
    }

}
