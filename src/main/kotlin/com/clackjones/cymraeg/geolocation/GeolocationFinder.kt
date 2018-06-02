package com.clackjones.cymraeg.geolocation

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth
import com.google.maps.model.LatLng

interface GeolocationFinder {
    fun findLocation(gwasanaeth : Gwasanaeth) : LatLng
}