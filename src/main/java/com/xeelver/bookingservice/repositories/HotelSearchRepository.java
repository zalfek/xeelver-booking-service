package com.xeelver.bookingservice.repositories;

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
public class HotelSearchRepository {

    public static final Logger LOGGER = Logger.getLogger(HotelSearchRepository.class.getName());

    @Value("${searchServiceBaseUrl}")
    private String searchServiceBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public JsonObject getHotelPrice(String offerId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LOGGER.info("Forwarding request to search Service: " + offerId);
        return restTemplate.exchange(searchServiceBaseUrl + "/api/v1/search/hotels/price/" + offerId, HttpMethod.GET,new HttpEntity<>(headers), JsonObject.class).getBody();
    }

}
