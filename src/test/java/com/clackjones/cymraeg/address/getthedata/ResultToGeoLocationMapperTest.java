package com.clackjones.cymraeg.address.getthedata;


import com.clackjones.cymraeg.address.GeoLocation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ResultToGeoLocationMapperTest {
    ResultToGeoLocationMapper mapper = new ResultToGeoLocationMapper();

    @Test
    public void shouldMapForValidResult() {
        // given
        Result result = new Result();
        Data data = new Data();

        data.setPostcode("SW1A 0AA");
        data.setEasting(530268);
        data.setNorthing(179545);
        data.setLatitude(51.499840);
        data.setLongitude(-0.124663);

        result.setData(data);

        // when
        GeoLocation geoLocation = mapper.map(result);

        // then
        assertThat(geoLocation.getPostcode(), equalTo(data.getPostcode()));
        assertThat(geoLocation.getEastings(), equalTo(data.getEasting()));
        assertThat(geoLocation.getNorthings(), equalTo(data.getNorthing()));
        assertThat(geoLocation.getLatitude(), equalTo(data.getLatitude()));
        assertThat(geoLocation.getLongitude(), equalTo(data.getLongitude()));
    }

}
