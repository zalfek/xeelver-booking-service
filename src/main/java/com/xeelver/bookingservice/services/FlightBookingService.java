package com.xeelver.bookingservice.services;

import com.google.gson.JsonObject;
import com.xeelver.bookingservice.repositories.AmadeusFlightBookingRepository;
import com.xeelver.bookingservice.repositories.PriceVerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class FlightBookingService implements FlightService{


    private final AmadeusFlightBookingRepository amadeusFlightBookingRepository;
    private final PriceVerificationRepository priceVerificationRepository;
    private static final Logger LOGGER = Logger.getLogger(FlightBookingService.class.getName());

    public JsonObject createFlightBooking(JsonObject request) {
        LOGGER.info("Verifying the price. Forwarding request to FlightSearchRepository.");
        JsonObject flightPrice = priceVerificationRepository.getFlightPrice(request);
        if (flightPrice.has("warnings")) {
            LOGGER.info("The price has changed. Sending back an updated offer status.");
            return flightPrice;
        } else {
            LOGGER.info("Price was successfully verified. Forwarding request to FlightBookingRepository.");
            return amadeusFlightBookingRepository.createFlightBooking(request).getResponse().getResult();
        }
    }

}
