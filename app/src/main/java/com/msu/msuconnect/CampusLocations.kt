package com.msu.msuconnect

import com.google.android.gms.maps.model.LatLng

// to obtain LatLng from here
// https://www.latlong.net/
class CampusLocations{
    // we need to add the other buildings + update the LatLng
    private val campusMap : HashMap<String, LatLng> = hashMapOf(
    "Tivoli" to LatLng(39.745140, -105.006030),                //"Tivoli"
    "King" to LatLng(39.743606, -105.006116),                  //"King Center"
    "Admin" to LatLng(39.741621, -105.009052),                 //"Administration Building"
    "7th St" to LatLng(39.742975, -105.008237),                //"7th Street Building"
    "StudentSucc" to LatLng(39.745895, -105.007360),           //"Student Success Building"
    "Boulder" to LatLng(39.740974, -105.002141),               //"Boulder Creek"
    "Cherry" to LatLng(39.741378, -105.001063),                //"Cherry Creek"
    "Central" to LatLng(39.742171, -105.002413),               //"Central Classroom"
    "West" to LatLng(39.742033, -105.003096),                  //"West Classroom"
    "Arts" to LatLng(39.742835, -105.003933),                  //"Arts Building"
    "Library" to LatLng(39.743441, -105.003064),               //"Auraria Library"
    "PE" to LatLng(39.744600, -105.003813),                    //"PE/Event Building"
    "Plaza" to LatLng(39.744113, -105.005080),                 //"Plaza Building"
    "Modular" to LatLng(39.741237, -105.007238),               //"Modulars"
    "Confluence" to LatLng(39.741394, -105.005035),            //"CCD Confluence"
    "Bear" to LatLng(39.740882, -105.004377),                  //"Bear Creek"
    "Regency" to LatLng(39.739612, -105.008447),               //"Regency Athletic Complex At Metro State University Denver"
    "Clear" to LatLng(39.742243, -105.001645),                 //"Clear Creek"
    "Church" to LatLng(39.742785, -105.002248),                //"St Elizabeth of Hungary Church"
    "StudentCommons" to LatLng(39.746446, -105.002063),        //"CU Student Commons"
    "Wellness Center" to LatLng(39.746792, -105.002374),       //"CU Wellness Center"
    "Business School" to LatLng(39.747473, -104.998207),       //"CU Business School"
    "Lawrence Street Center" to LatLng(39.746052, -104.999291),//"CU Lawrence Street Center"
    "CUBldg" to LatLng(39.746667, -104.999495),                //"CU Building"
    "Annex" to  LatLng(39.746900, -105.000010))                //"CU Building Annex"

    public fun getLocation(name: String): LatLng? {
        var returnVal = campusMap[name]
        if(returnVal == null)
            for(latLng in campusMap)
            {
                if(name.contains(latLng.key))
                    return latLng.value
            }
        else return campusMap[name]
        return null
    }
}
