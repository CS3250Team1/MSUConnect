package cs.msuconnectandroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_discover.*


class Discover : Fragment() {

    lateinit var editTextBox : EditText
    lateinit var button : Button

    companion object {
        fun newInstance() : Discover {
            return Discover()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_discover, container, false)
    }








}