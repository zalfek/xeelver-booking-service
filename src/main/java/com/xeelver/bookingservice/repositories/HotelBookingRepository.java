package com.xeelver.bookingservice.repositories;

import com.amadeus.resources.HotelBooking;
import com.google.gson.JsonObject;

public interface HotelBookingRepository {
    HotelBooking[] bookHotel(JsonObject bookingDescription);
}
