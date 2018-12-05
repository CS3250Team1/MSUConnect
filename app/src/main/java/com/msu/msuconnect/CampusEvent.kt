package com.msu.msuconnect

import com.google.android.gms.maps.model.LatLng
import java.util.*

class CampusEvent{
    private var mEventTitle : String
    private var mEventID : Int
    private var mEventLocation : String
    private var mEventLatLng : LatLng? = null
    private var mEventDate : Date? = null

    fun getLatLng() : LatLng? {return mEventLatLng}
    fun getTitle() : String {return mEventTitle}
    fun getEventID() : Int {return mEventID}
    fun getEventDate() : Date? {return mEventDate}
    constructor(mEventTitle: String, mEventID: Int, mEventLocation: String, locations : CampusLocations, month : Int, day : Int)
    {
        this.mEventTitle = mEventTitle
        this.mEventID = mEventID
        this.mEventLocation = mEventLocation
        this.mEventLatLng = locations.getLocation(mEventLocation)
        var cal = Calendar.getInstance()
        cal.set(2018, month, day, 0, 0)
        mEventDate = cal.time
    }
}