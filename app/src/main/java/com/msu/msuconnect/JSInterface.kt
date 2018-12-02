package com.msu.msuconnect

import android.util.Log
import android.webkit.JavascriptInterface

class JSInterface {
    private var mHtml = ""

    @JavascriptInterface
    fun processHTML(html: String) {
        //HTML content of the page
        mHtml = html
        Log.d("SOMAR", mHtml)
    }

    fun getHTML(): String {
        return mHtml
    }
}