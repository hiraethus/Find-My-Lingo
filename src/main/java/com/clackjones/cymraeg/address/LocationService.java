package com.clackjones.cymraeg.address;

import javax.xml.stream.Location;
import java.util.Optional;

public interface LocationService {
    boolean isValidPostcode(String postcode);
    Optional<GeoLocation> findLocationForPostcode(String postcode);
}
