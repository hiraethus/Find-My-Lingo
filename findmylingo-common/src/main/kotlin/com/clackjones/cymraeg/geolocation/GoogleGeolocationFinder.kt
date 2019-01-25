package com.clackjones.cymraeg.geolocation

import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.LatLng
import java.util.*

class GoogleGeolocationFinder(val context : GeoApiContext) : GeolocationFinder  {
    override fun findLocation(gwasanaeth: Gwasanaeth): Optional<LatLng> {
        val addr = addressString(gwasanaeth)
        val result = GeocodingApi.geocode(context, addr).await()

        if (result.size > 0) return Optional.of(result[0].geometry.location)
        else return Optional.empty()
    }

    private fun addressString(gwasanaeth: Gwasanaeth) : String {
        return arrayOf(
                gwasanaeth.cyfeiriadLlinellGyntaf,
                gwasanaeth.cyfeiriadAilLinell,
                gwasanaeth.cyfeiriadDinas,
                gwasanaeth.cyfeiriadSir,
                gwasanaeth.cyfeiriadCodPost
        ).joinToString()
    }
}