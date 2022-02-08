package com.xeelver.bookingservice.services;
import com.amadeus.resources.Resource;
import com.google.gson.JsonObject;
import com.xeelver.bookingservice.repositories.AmadeusHotelBookingRepository;
import com.xeelver.bookingservice.repositories.PriceVerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class HotelBookingService implements HotelService{

    private final AmadeusHotelBookingRepository amadeusHotelBookingRepository;
    private final PriceVerificationRepository priceVerificationRepository;
    private static final Logger LOGGER = Logger.getLogger(HotelBookingService.class.getName());


    public String createHotelBooking(Map<String, String> request){
        LOGGER.info("Verifying the price. Forwarding request to HotelSearchRepository.");
        JsonObject availability = priceVerificationRepository.getHotelPrice(request.get("hotelId"));
        if (!availability.get("available").getAsBoolean()){
            LOGGER.info("The offer is not available anymore.");
            return String.valueOf(availability);
        }else {
            LOGGER.info("Availability was successfully verified. Forwarding request to FlightBookingRepository.");
            return this.getData(amadeusHotelBookingRepository.bookHotel(availability));
        }
    }


    private String getData(Resource[] array) {
        String response = "";
        try {
            response = array == null ? null : String.valueOf(array[0].getResponse().getResult());
        } catch (Exception exception) {
            LOGGER.warning("FlightRepository returned an empty array. Exception: " + exception);
        }
        return response;
    }

}
