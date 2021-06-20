package com.xeelver.bookingservice.repositories;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchRepository {

    public static final Logger LOGGER = Logger.getLogger(FlightSearchRepository.class.getName());

    @Value("${searchServiceBaseUrl}")
    private String searchServiceBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private Gson gson = new Gson();

    public JsonObject getFlightPrice(JsonObject flightOffer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JsonObject offerData = flightOffer.get("data").getAsJsonObject().get("flightOffers").getAsJsonArray().get(0).getAsJsonObject();
        LOGGER.info("Forwarding request to the search service: " + offerData);
        String response =  restTemplate.exchange("http://34.89.58.148:80/api/v1/search/flights/price", HttpMethod.POST, new HttpEntity<>(offerData.toString(), headers), String.class).getBody(); //searchServiceBaseUrl +
        return gson.fromJson(response, JsonObject.class);
    }
}
