package cs.msuconnectandroid

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "trackName"
private const val ARG_PARAM2 = "positionLat"
private const val ARG_PARAM3 = "positionLng"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [trackMarker.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [trackMarker.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class trackMarker : Fragment() {
    // TODO: Rename and change types of parameters
    var trackName: String = "!"
    var positionLat: Double = 0.0
    var positionLng: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            trackName = it.getString(ARG_PARAM1)
            positionLat = it.getDouble(ARG_PARAM2)
            positionLng = it.getDouble(ARG_PARAM3)
        }
    }


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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment trackMarker.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: Double, param3: Double) =
                trackMarker().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putDouble(ARG_PARAM2, param2)
                        putDouble(ARG_PARAM3, param3)
                    }
                }
    }
}
