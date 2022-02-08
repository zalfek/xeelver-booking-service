package com.xeelver.bookingservice.services;

import com.google.gson.JsonObject;

public interface FlightService {
    JsonObject createFlightBooking(JsonObject request);
}
