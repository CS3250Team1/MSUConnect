package cs.msuconnectandroid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.SupportMapFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
*/

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [startWithMap.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [startWithMap.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class startWithMap : Fragment() {
    // TODO: Rename and change types of parameters
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.content_main, container, false)


        childFragmentManager
                .beginTransaction()
                .replace(R.id.map, SupportMapFragment.newInstance())
                .commitNow()
        //view: View = inflater.inflate(R.id.)
        //
        return view

    }

    /** Boiler plate OnCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }
*/
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
         * @return A new instance of fragment startWithMap.
         */
        // TODO: Rename and change types and number of parameters
        //@JvmStatic
        fun newInstance() : startWithMap {
            return startWithMap()//param1: String, param2: String
        }
                /**startWithMap().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }*/
    }
}
