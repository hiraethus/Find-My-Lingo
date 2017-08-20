package com.clackjones.cymraeg.address.getthedata;

import com.clackjones.cymraeg.address.GeoLocation;
import org.springframework.stereotype.Component;

@Component
public class ResultToGeoLocationMapper {
    public GeoLocation map(Result result) {
        Data data = result.getData();
        return new GeoLocation(data.getPostcode(),
                data.getEasting(), data.getNorthing(),
                data.getLatitude(), data.getLongitude());
    }
}
