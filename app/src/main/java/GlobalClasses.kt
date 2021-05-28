import java.util.*

val DEBUG_BUILD = true

val FLAG_ISNEWUSER = "ISNEWUSER"

class User(w:Double){
    var name:String = ""
    val birthdate:Date = Now()
    var height:Double = 0.0
    var weight:Double = 0.0

    constructor() : this(w=0.0) {

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