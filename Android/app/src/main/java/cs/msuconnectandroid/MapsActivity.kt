package cs.msuconnectandroid

import android.content.Intent
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




class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    var mAuth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mAuth.currentUser == null){
            setContentView(R.layout.activity_login)
        }else{
            setContentView(R.layout.activity_maps)
        }
        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        // Add a marker in Sydney and move the camera
        val msu = LatLng(39.743064, -105.006219)
        mMap.addMarker(MarkerOptions().position(msu).title("Marker in MSU Denver"))
        val zoomLevel = 16.0f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(msu, zoomLevel))
    }
}
