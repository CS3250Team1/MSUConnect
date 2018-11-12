package cs.msuconnectandroid

import org.jsoup.Jsoup
import java.text.DateFormat
import java.util.*

class CalendarScrapper {
    fun getEvents(html : String, locations: CampusLocations) : List<CampusEvent> {
        var events = ArrayList<CampusEvent>()
        // JSoup is the Java parser used to parse html
        var doc = Jsoup.parse(html)
        // get all elelments with the class below
        var elements = doc.getElementsByClass("twSimpleTableEventRow0_578854_429509")
        for(ele in elements) // iterate through every element and store the current iteration in ele
        {
            // gets the description, eventid, and location name
            var description = ele.getElementsByClass("twDescription")
            var eventID = ele.getElementsByAttribute("eventid").first().attributes()["eventid"]
            var location = ele.getElementsByClass("twLocation")
            // create a new CampusEvent and add it to our list
            events.add(CampusEvent(description.text(), eventID.toInt(), location.text(), locations ))
        }
        return events
    }
}