package cs.msuconnectandroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


class Settings : Fragment() {

    companion object {
        fun newInstance() : Settings {
            return Settings()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
        /*If private is selected as a setting turn off public if on
          If public is selected as a setting turn off private if on
          Taken care of because the selections are in radio group*/
    }



}