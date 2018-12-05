package com.msu.msuconnect

import org.jsoup.Jsoup
import java.text.DateFormat
import java.util.*

class CalendarScrapper {
    fun getEvents(html : String, locations: CampusLocations, startDate : Date?, endDate : Date?) : List<CampusEvent> {
        var events = ArrayList<CampusEvent>()
        // JSoup is the Java parser used to parse html
        var doc = Jsoup.parse(html)
        // get all elelments with the class below
        var elements = doc.getElementsByClass("twSimpleTableEventRow0_578854_429509")
        for(ele in elements) // iterate through every element and store the current iteration in ele
        {
            // gets the description, eventid, and location name
            var date = ele.getElementsByClass("twStartDate")
            var description = ele.getElementsByClass("twDescription")
            var eventID = ele.getElementsByAttribute("eventid").first().attributes()["eventid"]
            var location = ele.getElementsByClass("twLocation")
            var dateStrings = date.text().split(" ")
            // create a new CampusEvent and add it to our list
            var month = if(dateStrings[0].contains("Nov")) 10 else 11
            var event = CampusEvent(description.text(), eventID.toInt(), location.text(), locations, month, dateStrings[1].toInt())
            if(startDate == null || event.getEventDate()!! in startDate..endDate!!)
                events.add(event)
        }
        return events
    }
}