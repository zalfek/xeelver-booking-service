package com.xeelver.bookingservice.repositories;

import com.google.gson.JsonObject;

public interface VerificationRepository {
    JsonObject getFlightPrice(JsonObject flightOffer);
    JsonObject getHotelPrice(String offerId);
}
