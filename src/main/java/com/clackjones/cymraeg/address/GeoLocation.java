package com.clackjones.cymraeg.address;

public class GeoLocation {
    private String postcode;
    private long eastings;
    private long northings;
    private double latitude;
    private double longitude;

    public GeoLocation(String postcode, long eastings, long northings) {
        this.postcode = postcode;
        this.northings = northings;
        this.eastings = eastings;
    }

    public GeoLocation(String postcode, long eastings, long northings,
                       double latitude, double longitude) {
        this(postcode, eastings, northings);
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
