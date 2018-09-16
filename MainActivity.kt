package team.loginpagemsuconnect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_btn.setOnClickListener {
            var status = if(username_et.text.toString().equals("shahriar") && password_et.text.toString().equals("password"))
                "Logged in Successfully" else "Login fail"
            Toast.makeText(this,status,Toast.LENGTH_SHORT).show()

        }

    }
}
