package cs.msuconnectandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class Login :  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
                item.itemId == R.id.maps -> {
                    //setContentView(R.layout.activity_maps);
                    val intent = Intent(this@Login, MapsActivity::class.java)
                    startActivity(intent)
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
}