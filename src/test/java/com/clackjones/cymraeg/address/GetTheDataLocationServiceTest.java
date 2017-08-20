package com.clackjones.cymraeg.address;

import com.clackjones.cymraeg.address.getthedata.Data;
import com.clackjones.cymraeg.address.getthedata.GetTheDataPostcodeClient;
import com.clackjones.cymraeg.address.getthedata.Result;
import com.clackjones.cymraeg.address.getthedata.ResultToGeoLocationMapper;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.*;

public class GetTheDataLocationServiceTest {

    @Mock
    private GetTheDataPostcodeClient postcodeClient;

    @Mock
    private ResultToGeoLocationMapper mapper;

    @InjectMocks
    private GetTheDataLocationService getTheDataLocationService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void shouldReturnGeoLocationForValidRequest() {
        // given
        Result result = new Result();
        result.setStatus("match");
        result.setMatchType("unit_postcode");
        Data data = new Data();

        data.setPostcode("SW1A 0AA");
        data.setEasting(530268);
        data.setNorthing(179545);
        data.setLatitude(51.499840);
        data.setLongitude(-0.124663);

        result.setData(data);
        ResponseEntity<Result> responseObj = new ResponseEntity<Result>(result, null, HttpStatus.OK);

        given(postcodeClient.findLocationForPostcode("SW1A 0AA")).willReturn(responseObj);
        given(mapper.map(responseObj.getBody())).willReturn(new GeoLocation("SW1A 0AA", 530268,
                179545, 51.499840, -0.124663));

        // when
        Optional<GeoLocation> geoLocation = getTheDataLocationService.findLocationForPostcode("SW1A 0AA");

        // then
        assertThat(geoLocation.isPresent(), equalTo(true));
        verify(mapper, times(1)).map(responseObj.getBody());
    }

    @Test
    public void shouldReturnEmptyGeoLocationWhenHttpStatusNotOk() {
        // given
        Result result = new Result();
        ResponseEntity<Result> responseObj = new ResponseEntity<Result>(result, null, HttpStatus.NOT_FOUND);

        given(postcodeClient.findLocationForPostcode("SW1A 0AA")).willReturn(responseObj);
        given(mapper.map(responseObj.getBody())).willReturn(new GeoLocation("SW1A 0AA", 530268,
                179545, 51.499840, -0.124663));

        // when
        Optional<GeoLocation> geoLocation = getTheDataLocationService.findLocationForPostcode("SW1A 0AA");

        // then
        assertThat(geoLocation.isPresent(), equalTo(false));
    }

    @Test
    public void shouldReturnEmptyGeoLocationWhenStatusNoMatch() {
        // given
        Result result = new Result();
        result.setStatus("no_match");
        ResponseEntity<Result> responseObj = new ResponseEntity<Result>(result, null, HttpStatus.OK);

        given(postcodeClient.findLocationForPostcode("SW1A 0AA")).willReturn(responseObj);
        given(mapper.map(responseObj.getBody())).willReturn(new GeoLocation("SW1A 0AA", 530268,
                179545, 51.499840, -0.124663));

        // when
        Optional<GeoLocation> geoLocation = getTheDataLocationService.findLocationForPostcode("SW1A 0AA");

        // then
        assertThat(geoLocation.isPresent(), equalTo(false));
    }

    @Test
    public void shouldReturnEmptyGeoLocationWhenNotTypeUnitPostcode() {
        // given
        Result result = new Result();
        result.setStatus("match");
        result.setMatchType("postcode_district");
        ResponseEntity<Result> responseObj = new ResponseEntity<Result>(result, null, HttpStatus.OK);

        given(postcodeClient.findLocationForPostcode("SW1A 0AA")).willReturn(responseObj);
        given(mapper.map(responseObj.getBody())).willReturn(new GeoLocation("SW1A 0AA", 530268,
                179545, 51.499840, -0.124663));

        // when
        Optional<GeoLocation> geoLocation = getTheDataLocationService.findLocationForPostcode("SW1A 0AA");

        // then
        assertThat(geoLocation.isPresent(), equalTo(false));
    }
}
