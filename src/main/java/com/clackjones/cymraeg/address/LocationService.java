package com.clackjones.cymraeg.address;

import javax.xml.stream.Location;
import java.util.Optional;

public interface LocationService {
    default boolean isValidPostcode(String postcode) {
        return findLocationForPostcode(postcode).isPresent();
    }

    Optional<GeoLocation> findLocationForPostcode(String postcode);
}
