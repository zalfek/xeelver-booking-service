package com.xeelver.bookingservice.repositories;

import com.amadeus.resources.FlightOrder;
import com.google.gson.JsonObject;

public interface FlightBookingRepository {
    FlightOrder createFlightBooking(JsonObject bookingDescription);
}
