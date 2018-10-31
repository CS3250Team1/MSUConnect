package cs.msuconnectandroid

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.model.value.IntegerValue


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

//lateinit var trackObjects:Array<trackMarker>

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TrackFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TrackFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TrackFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Replace this with a getter function that reads from the user search
        val trackNames = arrayOf(
                "Michael Boykin", "Bradley Isaacs", "Rin Isobe", "MD Alam", "Omar Martinez",
                "Raymond Ortiz", "Austin Gailey", "Michael Jordan", "Keanu Reeves", "Steven Speilberg")
        val trackLats = arrayOf(
                39.44380, 39.44400, 39.44360, 39.44320, 39.44380,
                39.44330, 39.44270, 39.44400, 39.44450, 39.44410)

        val trackLngs = arrayOf(
                -105.0028, -105.0024, -105.0011, -105.0011, -105.0017,
                -105.0029, -105.0007, -105.0009, -105.0016, -105.0017)
        //End TODO block

        var trackObjects = ArrayList<trackMarker>()
        for (index in 0..9){
            var point:trackMarker = trackMarker.newInstance(
                trackNames[index],
                trackLats[index],
                trackLngs[index])
            trackObjects.add(point)}
        }



    }

    // TODO: Rename method, update argument and hook method into UI event

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

/**    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TrackFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TrackFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
*/