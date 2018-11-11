package cs.msuconnectandroid.MSUConnectObjects

import com.google.firebase.auth.FirebaseUser

data class User(var name : String) {

    lateinit var firebaseUser   : FirebaseUser
    lateinit var email          : String
    lateinit var groups         : List<Group>



}