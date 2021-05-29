import java.util.*

val DEBUG_BUILD = true

val FLAG_ISNEWUSER = "ISNEWUSER"

open class User(
    var name      :String   = "anon",
    val birthdate :Date     = Date(0),
    var height    :Double   = 0.0,
    var sex       :Int      = 0,
    var weight    :Double   = 0.0,
    var lastAccess:Date     = Date(0)
){


    fun setData(n:String, b:String, h:String, s:String): User {

        if(n != "") {name = n}
        //if(b != "") {birthdate   = h.toDouble()}
        if(h != "") {height      = h.toDouble()}
        if(s=="M")  {sex = 1}else if(s == "F"){sex=2}
        println("AaaaaaaSAAAAAAAAAAAAAAAAAAAAA "+s+ " "+sex)
        return this
    }

}


class weightReport(w:Double){
    val date = Now()
    var weight = w

    constructor() : this(w=0.0) {

    }
}




fun Now(): Date {

    return Calendar.getInstance().time
}