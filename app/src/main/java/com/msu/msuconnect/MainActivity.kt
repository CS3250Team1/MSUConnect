package com.msu.msuconnect

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout : DrawerLayout
    private lateinit var mNaviBar : NavigationView
    private lateinit var mJSI : JSInterface
    var mWebViewShown = false
    var mStartDate : Date? = null
    var mEndDate : Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mJSI = JSInterface()

        // create toolbar with hamburger menu icon
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_hamburger)
        }

        // create the slide out menu and setup the onClick
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNaviBar = findViewById(R.id.nav_view)
        mNaviBar.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()

            // Change fragments here
            when(menuItem.itemId){
                R.id.login -> { updateUI(false)}
                R.id.logout -> { updateUI(true)}
            }
            changeFragment(menuItem.itemId)
            true
        }
        changeFragment(R.id.login)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun showWebView(showV : Boolean, url : String) {
        mWebViewShown = showV
        var lp = findViewById<WebView>(R.id.webView)
        var parm = lp.layoutParams as LinearLayout.LayoutParams
        parm.weight = if(showV) 1.0f else 0.0f
        lp.layoutParams = parm
        if(showV){             // load webview
            var webView = findViewById<WebView>(R.id.webView)
            webView.settings.javaScriptEnabled = true
            webView.addJavascriptInterface(mJSI, "HTMLOUT")
            webView!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url)
                    return true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    webView.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');")
                    super.onPageFinished(view, url)
                }
            }
            webView!!.loadUrl(url)
        }
    }

    fun updateUI(loggedIn : Boolean) {
        if(loggedIn) {
            mNaviBar.menu.findItem(R.id.login).isVisible = false
            mNaviBar.menu.findItem(R.id.logout).isVisible = true
        }
        else {
            mNaviBar.menu.findItem(R.id.login).isVisible = true
            mNaviBar.menu.findItem(R.id.logout).isVisible = false
        }
    }

    public fun changeFragment(newFragmentId : Int)
    {
        showWebView(false, "") // turns off webview
        when(newFragmentId){
            R.id.login -> { supportFragmentManager.beginTransaction().replace(R.id.fragmentContent, LoginFragment()).commit() }
            R.id.logout -> {
                updateUI(false)
                var loginFrag = LoginFragment()
                var bundle = Bundle()
                bundle.putBoolean("logout", true)
                loginFrag.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContent, loginFrag).commit()
            }
            R.id.settings -> { supportFragmentManager.beginTransaction().replace(R.id.fragmentContent, Settings()).commit() }
            R.id.about -> { supportFragmentManager.beginTransaction().replace(R.id.fragmentContent, AboutFragment()).commit() }
            R.id.maps -> {
                updateUI(true)
                var mapFrag = MapsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContent, mapFrag).commit()
                mapFrag.setStartEndDate(mStartDate, mEndDate)
                mapFrag.getMapAsync(mapFrag)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    public override fun onStart() {
        super.onStart()
    }
}
