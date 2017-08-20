package com.clackjones.cymraeg.address;

import com.clackjones.cymraeg.address.getthedata.GetTheDataPostcodeClient;
import com.clackjones.cymraeg.address.getthedata.Result;
import com.clackjones.cymraeg.address.getthedata.ResultToGeoLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Implementation of the LocationService which uses the
 * GetTheData REST web service to find GeoLocations of postcodes.
 */
@Service
public class GetTheDataLocationService implements LocationService {

    @Autowired
    private ResultToGeoLocationMapper resultToGeoLocationMapper;
    @Autowired
    private GetTheDataPostcodeClient postcodeClient;

    @Override
    public Optional<GeoLocation> findLocationForPostcode(String postcode) {
        ResponseEntity<Result> response = postcodeClient.findLocationForPostcode(postcode);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            return Optional.empty();
        }

        Result result = response.getBody();
        if ("no_match".equals(result.getStatus()) || !"unit_postcode".equals(result.getMatchType()) ) {
            return Optional.empty();
        }

        GeoLocation gl = resultToGeoLocationMapper.map(result);
        return Optional.of(gl);
    }
}
