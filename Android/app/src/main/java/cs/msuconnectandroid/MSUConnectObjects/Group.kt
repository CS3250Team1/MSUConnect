package cs.msuconnectandroid.MSUConnectObjects

data class Group(var name : String){

    lateinit var Users      : List<User>
    lateinit var Groups     : List<Group>
    

}