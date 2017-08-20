package com.clackjones.cymraeg.address.getthedata;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client to call GetTheData Postcode service
 */
@Component
public class GetTheDataPostcodeClient {
    private final String POSTCODE_URL = "http://api.getthedata.com/postcode/%s";

    public ResponseEntity<Result> findLocationForPostcode(String postcode) {
        String postcodeUrl = String.format("http://api.getthedata.com/postcode/%s", postcode);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForEntity(postcodeUrl, Result.class);
    }
}
