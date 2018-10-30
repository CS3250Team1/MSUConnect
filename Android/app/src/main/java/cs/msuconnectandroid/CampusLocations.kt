package cs.msuconnectandroid

import com.google.android.gms.maps.model.LatLng

// to obtain LatLng from here
// https://www.latlong.net/
class CampusLocations{
    // we need to add the other buildings + update the LatLng
    private val campusMap : HashMap<String, LatLng> = hashMapOf(
            "Tivoli" to LatLng(1.0, 1.0),
            "Aero" to LatLng(1.0, 1.0))

    public fun getLocation(name: String): LatLng? {
        return campusMap[name]
    }
}