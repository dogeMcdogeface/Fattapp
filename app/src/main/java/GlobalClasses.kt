import java.util.*
import kotlin.collections.HashMap


val DEBUG_BUILD = true

val FLAG_ISNEWUSER = "ISNEWUSER"

open class User(){
    /*************USER DATA****************************/
    var name      :String   = "anon"
    val birthdate :Date     = Date(0)
    var height    :Double   = 0.0
    var sex       :Int      = 0

    /*************USER PAST STATS**********************/
    var weight: DatedMeasure = DatedMeasure()
    var wrist: DatedMeasure = DatedMeasure()
    var waist: DatedMeasure = DatedMeasure()


    /***********SYSTEM DATA****************************/
    var lastAccess:Date     = Date(0)


    fun setData(n:String, b:String, h:String, s:String): User {
        if(n != "") {name = n}
        //if(b != "") {birthdate   = h.toDouble()}
        if(h != "") {height      = h.toDouble()}
        if(s=="M")  {sex = 1}else if(s == "F"){sex=2}
        return this
    }
}


class DatedMeasure (
    var history:  HashMap<String, Double> =  HashMap<String, Double>()
){

    var last: Double
        get() {
            if(history.size<1){return 0.0}
            return history[Collections.max(history.keys)]!!
        }
    set(value){
        history[Today().time.toString()] = value
    }

    var today: Double
        get() {
            var t:Double? = history[Today().time.toString()]
            if(t == null){return 0.0}
            return t
        }
        set(value){
            history[Today().time.toString()] = value
        }
}



fun Now(): Date {
    return Calendar.getInstance().time
}
fun Today():Date{   //get time at beginning of today
    var cal:Calendar = GregorianCalendar()
    cal.time = Now()
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    var date:Date = cal.time
    return cal.getTime()
}