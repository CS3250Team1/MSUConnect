package cs.msuconnectandroid

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.util.Log
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.PolygonOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    // location stuff
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        // 3
        private const val REQUEST_CHECK_SETTINGS = 2
    }

    var mAuth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mAuth.currentUser == null) {
            setContentView(R.layout.activity_login)
        } else {
            setContentView(R.layout.activity_maps)
        }
        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                val currentLatLng = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
        createLocationRequest()
    }

    // code to get current location
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
                return
        }
        mMap.isMyLocationEnabled = true;
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }
    }

    private fun startLocationUpdates() {
        //1
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        //2
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    private fun createLocationRequest() {
        // 1
        locationRequest = LocationRequest()
        // 2
        locationRequest.interval = 10000
        // 3
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        // 4
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        // 5
        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            // 6
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(this@MapsActivity,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    // 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }
    }

    // 2
    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // 3
    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when {
                item.itemId == R.id.discover -> {
                    setContentView(R.layout.activity_discover);
                }
                item.itemId == R.id.logout -> {
                    val intent = Intent(this@MapsActivity, Login::class.java)
                    startActivity(intent)
                    FirebaseAuth.getInstance().signOut()
                }
                item.itemId == R.id.profile -> {
                    setContentView(R.layout.activity_profile);
                }
                item.itemId == R.id.settings -> {
                    setContentView(R.layout.activity_settings);
                }
            }
        }
        return true
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val aheu = PolygonOptions()
        aheu.fillColor(Color.RED)
        aheu.clickable(true)
        aheu.add(LatLng(39.743115, -105.006219))
        aheu.add(LatLng(39.743922, -105.006919))
        aheu.add(LatLng(39.744289, -105.005972))
        aheu.add(LatLng(39.743434, -105.005331))
        var aheuPL = googleMap!!.addPolygon(aheu)

        val tivoli = PolygonOptions()
        tivoli.fillColor(Color.RED)
        tivoli.clickable(true)
        tivoli.add(LatLng(39.74453774, -105.00595106))
        tivoli.add(LatLng(39.74548642, -105.00667526))
        tivoli.add(LatLng(39.74596076, -105.00569894))
        tivoli.add(LatLng(39.74492959, -105.00493182))
        var tivoliPL = googleMap!!.addPolygon(tivoli)

        val msub = PolygonOptions()
        msub.fillColor(Color.RED)
        msub.clickable(true)
        msub.add(LatLng(39.74350294, -105.00504351))
        msub.add(LatLng(39.74373393, -105.00522322))
        msub.add(LatLng(39.7437793, -105.00512398))
        msub.add(LatLng(39.74408041, -105.00537879))
        msub.add(LatLng(39.7440723, -105.00542902))
        msub.add(LatLng(39.74409756, -105.00544981))
        msub.add(LatLng(39.7441749, -105.00529357))
        msub.add(LatLng(39.74438269, -105.00545868))
        msub.add(LatLng(39.744591, -105.004995))
        msub.add(LatLng(39.74445189, -105.00487509))
        msub.add(LatLng(39.74440011, -105.00496103))
        msub.add(LatLng(39.74434855, -105.00491946))
        msub.add(LatLng(39.74442434, -105.00474981))
        msub.add(LatLng(39.74412612, -105.00451861))
        msub.add(LatLng(39.74387582, -105.00505081))
        msub.add(LatLng(39.74359779, -105.00483881))
        var msubPL = googleMap!!.addPolygon(msub)

        val aero = PolygonOptions()
        aero.fillColor(Color.RED)
        aero.clickable(true)
        aero.add(LatLng(39.744748, -105.008936))
        aero.add(LatLng(39.744781, -105.008989))
        aero.add(LatLng(39.744748, -105.009032))
        aero.add(LatLng(39.744863, -105.009123))
        aero.add(LatLng(39.744876, -105.009102))
        aero.add(LatLng(39.744946, -105.009182)) //
        aero.add(LatLng(39.744971, -105.009135))
        aero.add(LatLng(39.744991, -105.009172))
        aero.add(LatLng(39.745428, -105.008561)) //
        aero.add(LatLng(39.745362, -105.008470))
        aero.add(LatLng(39.745354, -105.008486))
        aero.add(LatLng(39.745152, -105.008250))
        aero.add(LatLng(39.745094, -105.008320))
        aero.add(LatLng(39.745144, -105.008374))
        var aeroPL = googleMap!!.addPolygon(aero)

        // Add a marker in Sydney and move the camera
        val msu = LatLng(39.743064, -105.006219)
        mMap.addMarker(MarkerOptions().position(msu).title("Marker in MSU Denver"))
        val zoomLevel = 16.0f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(msu, zoomLevel))
        setUpMap()

    }
}
