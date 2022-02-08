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
public class PriceVerificationRepository implements VerificationRepository{

    public static final Logger LOGGER = Logger.getLogger(PriceVerificationRepository.class.getName());

    @Value("${searchServiceBaseUrl}")
    private String searchServiceBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private Gson gson = new Gson();

    public JsonObject getFlightPrice(JsonObject flightOffer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JsonObject offerData = flightOffer.get("data").getAsJsonObject().get("flightOffers").getAsJsonArray().get(0).getAsJsonObject();
        LOGGER.info("Forwarding request to the search service: " + offerData);
        String response =  restTemplate.exchange(searchServiceBaseUrl + "/api/v1/search/flights/price", HttpMethod.POST, new HttpEntity<>(offerData.toString(), headers), String.class).getBody(); //searchServiceBaseUrl +
        return gson.fromJson(response, JsonObject.class);
    }


    public JsonObject getHotelPrice(String offerId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LOGGER.info("Forwarding request to search Service: " + offerId);
        return restTemplate.exchange(searchServiceBaseUrl + "/api/v1/search/hotels/price/" + offerId, HttpMethod.GET,new HttpEntity<>(headers), JsonObject.class).getBody();
    }

}
