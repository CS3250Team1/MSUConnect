package cs.msuconnectandroid

import com.google.android.gms.maps.model.LatLng

// to obtain LatLng from here
// https://www.latlong.net/
class CampusLocations{
    // we need to add the other buildings + update the LatLng
    private val campusMap : HashMap<String, LatLng> = hashMapOf(
            "Tivoli" to LatLng(39.745140, -105.006030),
            "King Center" to LatLng(39.743606, -105.006116),
            "Administration Building" to LatLng(39.741621, -105.009052),
            "7th Street Building" to LatLng(39.742975, -105.008237),
            "Student Success Building" to LatLng(39.745895, -105.007360),
            "Boulder Creek" to LatLng(39.740974, -105.002141),
            "Cherry Creek" to LatLng(39.741378, -105.001063),
            "Centrel Classroom" LatLng(39.742171, -105.002413),
            "West Classroom" to LatLng(39.742033, -105.003096),
            "Arts Building" to LatLng(39.742835, -105.003933),
            "Auraria Libaray" to LatLng(39.743441, -105.003064),
            "PE/Event Building" to LatLng(39.744600, -105.003813),
            "Plaza Building" to LatLng(39.744113, -105.005080),
            "Modulors" to LatLng(39.741237, -105.007238),
            "CCD Confluence" to LatLng(39.741394, -105.005035),
            "Bear Creek" to LatLng(39.740882, -105.004377),
            "Regency Athletic Complex At Metro State University Denver" to LatLng(39.739612, -105.008447),
            "Clear Creek" to LatLng(39.742243, -105.001645),
            "St Elizabeth of Hungary Church" to LatLng(39.742785, -105.002248),
            "CU Student Commons" to LatLng(39.746446, -105.002063),
            "CU Wellness Center" to LatLng(39.746792, -105.002374),
            "CU Business School" to LatLng(39.747473, -104.998207),
            "CU Lawrence Street Center" to LatLng(39.746052, -104.999291),
            "CU Building" to LatLng(39.746667, -104.999495),
            "CU Building Annex" to  LatLng(39.746900, -105.000010)

    public fun getLocation(name: String): LatLng? {
        return campusMap[name]
    }
}
