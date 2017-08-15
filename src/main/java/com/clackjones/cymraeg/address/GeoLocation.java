package com.clackjones.cymraeg.address;

public class GeoLocation {
    private String postcode;
    private long eastings;
    private long northings;

    public GeoLocation(String postcode, long eastings, long northings) {
        this.postcode = postcode;
        this.northings = northings;
        this.eastings = eastings;
    }

    public String getPostcode() {
        return postcode;
    }

    public long getNorthings() {
        return northings;
    }

    public long getEastings() {
        return eastings;
    }
}
