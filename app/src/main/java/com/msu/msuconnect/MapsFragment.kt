package com.msu.msuconnect

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.annotation.RawRes
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsFragment : SupportMapFragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    // location stuff
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var mEvents: List<CampusEvent> = emptyList()
    private var mStartDate : Date? = null
    private var mEndDate : Date? = null
    private lateinit var mHtml : String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.activity!!)
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map as GoogleMap
        // Add a marker in Aurora
        val msu = LatLng(39.743064, -105.006219)
        val zoomLevel = 16.0f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(msu, zoomLevel))

        val locations = CampusLocations()
        val scraper = CalendarScrapper()
        mEvents = scraper.getEvents(readRaw(R.raw.msu_event), locations, mStartDate, mEndDate)

        for(event in mEvents)
        {
            var eLatLng = event.getLatLng()
            if(eLatLng != null)
            {
                var randomList = (-2..2).shuffled()
                var xOffset = randomList.first() / 10000.0
                var yOffset = randomList.last() / 10000.0
                var marker = mMap.addMarker(MarkerOptions().position(LatLng(eLatLng.latitude + xOffset, eLatLng.longitude + yOffset)).title(event.getTitle()))
                marker.tag = event.getEventID()
                mMap.setOnMapClickListener {
                    var parent = this.activity as MainActivity
                    if(parent.mWebViewShown)
                        parent.showWebView(false, "https://msudenver.edu/events/")
                }
                mMap.setOnInfoWindowClickListener { p0 ->
                    if(p0 != null)
                    {
                        var eventID = p0.tag as Int
                        if(eventID > 0)
                            (this.activity as MainActivity).showWebView(true, openURL(eventID.toString()))
                } }
            }
        }
        setUpMap()
    }

    // code to get current location
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this.context!!, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.activity!!, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener{ location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    fun openURL(url : String) : String {
        return "https://msudenver.edu/events/?trumbaEmbed=view%3Devent%26eventid%3D$url"
    }

    fun readRaw(@RawRes resourceId: Int): String {
        return resources.openRawResource(resourceId).bufferedReader(Charsets.UTF_8).use { it.readText() }
    }

    fun setStartEndDate(startDate : Date?, endDate : Date?) {
        mStartDate = startDate
        mEndDate = endDate
    }
}