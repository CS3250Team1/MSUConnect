package cs.msuconnectandroid.MSUConnectObjects

data class User(var name : String) {

    lateinit var email       : String
    lateinit var classes     : List<String>
    lateinit var Groups      : List<Group>

}