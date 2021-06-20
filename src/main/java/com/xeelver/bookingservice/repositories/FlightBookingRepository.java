package com.xeelver.bookingservice.repositories;


import com.amadeus.Amadeus;
import com.amadeus.resources.FlightOrder;
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
public class FlightBookingRepository {

    public static final Logger LOGGER = Logger.getLogger(FlightBookingRepository.class.getName());

    @Value("${amadeusApiKey}")
    private String amadeusApiKey;

    @Value("${amadeusApiSecret}")
    private String amadeusApiSecret;

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
    public FlightOrder createFlightBooking(JsonObject bookingDescription) {
        return amadeus.booking.flightOrders.post(bookingDescription);
    }

}
