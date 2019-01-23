package com.clackjones.cymraeg.geolocation

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth
import com.google.maps.model.LatLng
import java.util.*

interface GeolocationFinder {
    fun findLocation(gwasanaeth : Gwasanaeth) : Optional<LatLng>
}