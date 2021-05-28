import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


var auth:     FirebaseAuth      = Firebase.auth
val database: FirebaseDatabase  = Firebase.database("https://fattapp-7014c-default-rtdb.europe-west1.firebasedatabase.app/")
val storage: FirebaseStorage    = Firebase.storage



var firebaseHelper = FirebaseHelper()
var currentUser = CurrentUserHelper()

class FirebaseHelper {
    //private var reports = HashMap<String, Report>()
    private var images = HashMap<String, Bitmap?>()
    //private var users = HashMap<String, User>()
    private var downloadTasks = HashMap<String, FileDownloadTask>()
    private var updateListeners = ArrayList<() -> Unit>()

    private var usersReference = database.getReference("/users")



    fun addUpdateListener(f: () -> Unit) {
        updateListeners.add(f)
    }

    fun userLoggedIn(){ //a@a.aa
        println(auth.currentUser?.uid.toString())
        currentUser.setReference(usersReference.child(auth.currentUser?.uid.toString()))
        currentUser.onLogin()
    }
}

class CurrentUserHelper{
    var user = User()
    private var reference:DatabaseReference? = null

    fun setReference(r: DatabaseReference) {
        reference = r
    }
    fun onLogin(){
        reference?.child("lastAccess")?.setValue(Now())
    }

    fun sendReport(wReport: weightReport) {
        reference?.child("wReport")?.setValue(wReport)
    }

}