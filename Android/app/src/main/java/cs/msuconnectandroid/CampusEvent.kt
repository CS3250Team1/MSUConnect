package cs.msuconnectandroid

import com.google.android.gms.maps.model.LatLng
import java.util.*

class CampusEvent{
    private var mEventTitle : String
    private var mEventID : Int
    private var mEventLocation : String
    private var mEventLatLng : LatLng? = null

    fun getLatLng() : LatLng? {return mEventLatLng}
    fun getTitle() : String {return mEventTitle}
    fun getEventID() : Int {return mEventID}
    constructor(mEventTitle: String, mEventID: Int, mEventLocation: String, locations : CampusLocations)
    {
        this.mEventTitle = mEventTitle
        this.mEventID = mEventID
        this.mEventLocation = mEventLocation
        this.mEventLatLng = locations.getLocation(mEventLocation)
    }
}