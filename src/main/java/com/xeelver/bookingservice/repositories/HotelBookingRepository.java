package com.xeelver.bookingservice.repositories;

import com.amadeus.Amadeus;
import com.amadeus.resources.HotelBooking;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class HotelBookingRepository {

    @Value("${amadeusApiKey}")
    private String amadeusApiKey;

    @Value("${amadeusApiSecret}")
    private String amadeusApiSecret;

    public static final Logger LOGGER = Logger.getLogger(HotelBookingRepository.class.getName());

    private Amadeus amadeus;

    @PostConstruct
    public void init() {
        amadeus = Amadeus
                .builder(amadeusApiKey, amadeusApiSecret)
                .setLogger(LOGGER)
                .setLogLevel("debug")
                .build();
    }

    @SneakyThrows
    public HotelBooking[] bookHotel(JsonObject bookingDescription) {
        return amadeus.booking.hotelBookings.post(bookingDescription);
    }

}
