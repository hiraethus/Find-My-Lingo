package com.clackjones.cymraeg.address;


import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class OSLocationServiceTest {
    private final OSLocationService service = new OSLocationService();

    @Test
    public void shouldReturnEmptyWhenInvalidPostcode() {
        // given
        String invalidPostcode = "1B";

        // when
        Optional<GeoLocation> location = service.findLocationForPostcode(invalidPostcode);

        // then
        assertThat(location, notNullValue());
        assertThat(location.isPresent(), is(false));
    }

    @Test
    public void shouldReturnGeoLocationWithNorthingsAndEastingsWhenValidPostcode() {
        // given
        String invalidPostcode = "BN1 1AR";

        // when
        Optional<GeoLocation> location = service.findLocationForPostcode(invalidPostcode);

        // then
        assertThat(location, notNullValue());
        assertThat(location.isPresent(), is(true));

        GeoLocation geoLocation = location.get();
        assertThat(geoLocation.getPostcode(), equalTo(invalidPostcode));
        assertThat(geoLocation.getEastings(), equalTo(530877L));
        assertThat(geoLocation.getNorthings(), equalTo(104257L));
    }

    @Test
    public void shouldReturnGeoLocationWithNorthingsAndEastingsWhenValidPostcodeNoSpaces() {
        // given
        String invalidPostcode = "FK95SQ";

        // when
        Optional<GeoLocation> location = service.findLocationForPostcode(invalidPostcode);

        // then
        assertThat(location, notNullValue());
        assertThat(location.isPresent(), is(true));

        GeoLocation geoLocation = location.get();
        assertThat(geoLocation.getPostcode(), equalTo("FK9 5SQ"));
        assertThat(geoLocation.getEastings(), equalTo(280005L));
        assertThat(geoLocation.getNorthings(), equalTo(695666L));
    }
}
