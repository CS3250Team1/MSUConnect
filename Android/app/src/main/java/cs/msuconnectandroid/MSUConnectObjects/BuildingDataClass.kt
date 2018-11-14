package cs.msuconnectandroid.MSUConnectObjects

import com.google.firebase.firestore.GeoPoint
import java.util.*

data class BuildingDataClass(
        val name: String,
        var markers: Array<GeoPoint>
)
