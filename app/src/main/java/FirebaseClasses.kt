import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


var auth:     FirebaseAuth      = Firebase.auth
val database: FirebaseDatabase  = Firebase.database("https://fattapp-7014c-default-rtdb.europe-west1.firebasedatabase.app/")
val storage: FirebaseStorage    = Firebase.storage




