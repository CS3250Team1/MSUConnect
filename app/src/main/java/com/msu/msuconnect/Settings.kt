package com.msu.msuconnect

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import java.util.*


class Settings : Fragment() {

    companion object {
        fun newInstance(): Settings {
            return Settings()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        var calMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
        var calDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var calYear = Calendar.getInstance().get(Calendar.YEAR)

        /*If private is selected as a setting turn off public if on
          If public is selected as a setting turn off private if on
          Taken care of because the selections are in radio group*/

        //Set Start Date

        view.calendarView.setOnDateChangeListener { calendarView, i1, i2, i3 ->
            calYear = i1
            calMonth = i2 + 1
            calDay = i3
        }

        view.Button_StartDate.setOnClickListener {
            view.textViewStart.text = "$calMonth/$calDay/$calYear"
            val rtnStartMonth = calMonth
            val rtnStartDay = calDay
            val rtnStartYear = calYear
            val rtnStartDate = calendarView.getDate()
        }

        //Set End Date
        view.Button_EndDate.setOnClickListener {
            view.textViewEnd.text = "$calMonth/$calDay/$calYear"
            val rtnEndMonth = calMonth
            val rtnEndDay = calDay
            val rtnEndYear = calYear
            val rtnEndDate = calendarView.getDate()
        }
        return view
    }

}