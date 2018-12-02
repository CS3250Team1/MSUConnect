package com.msu.msuconnect

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class Profile : Fragment() {

    companion object {
        fun newInstance() : Profile {
            return Profile()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //val switchStatus = activity?.findViewById<Switch>(R.id.switch1)
        //setContentView(R.layout.activity_profile)
        //val buttonPress: Button = findViewById(R.id.ProfileView_FormTitle_Interests)
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        view.ProfileView_FormTitle_Interests.setOnClickListener {
            if(switch1.isChecked) textView6.visibility = View.GONE
            else textView6.visibility = View.VISIBLE
        }

        return view

    }

}
