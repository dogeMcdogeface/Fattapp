import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
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
    private var users = HashMap<String, User>()
    private var downloadTasks = HashMap<String, FileDownloadTask>()
    private var updateListeners = ArrayList<() -> Unit>()

    private var usersReference = database.getReference("/users")


    fun addUpdateListener(f: () -> Unit) {
        updateListeners.add(f)
    }
    fun onUpdate() {
        println("Received Database Update")
        for (f in updateListeners) {
            f()
        }
    }

    private val onUserUpdate: ValueEventListener =  object:ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val id = snapshot.key
            val newUser = snapshot.child("userInfo").getValue<User>()
            if(id != null && newUser != null) {
                users[id] = newUser
                onUpdate()
            }
        }
        override fun onCancelled(error: DatabaseError) {
            //println(databaseError.toException())
        }
    }


    fun getUserById(userId: String?): User? {
        return users[userId]
    }
    fun trackUser(id:String): DatabaseReference {
        var userRef = usersReference.child(id)
        userRef.addValueEventListener(onUserUpdate)
        return userRef
    }
}

class CurrentUserHelper{
    var r = auth.addAuthStateListener({onLogin()})

    var id = ""
    val mail: String?
        get() = auth.currentUser?.email

    var info:User?
        get() {return firebaseHelper.getUserById(id)}
        set(value) {reference?.child("userInfo")?.setValue(value)}


    lateinit private var reference :DatabaseReference
    fun setReference(r: DatabaseReference) {
        reference = r
    }

    fun onLogin() {
        id = auth.currentUser?.uid.toString()
        reference = firebaseHelper.trackUser(id)
        reference?.child("lastAccess")?.setValue(Now())
    }

    fun sendReport(wReport: weightReport) {
        reference?.child("wReport")?.setValue(wReport)
    }
}