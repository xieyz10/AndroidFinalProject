package com.example.firebase

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity: AppCompatActivity() {
    lateinit var context: Context
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        context = this@RegisterActivity
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("User")
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
        }
    }

    fun btnRegister_submit_pressed(view: View){
        if(view.id == R.id.btn_register_submit){
            val userName = findViewById<EditText>(R.id.editText_userName).text.toString()
            val password = findViewById<EditText>(R.id.editText_password).text.toString()

            if(userName.length == 0){
                Toast.makeText( context,"Empty user name!", Toast.LENGTH_LONG).show()
            }else if(password.length <6){
                Toast.makeText( context,"Password too short!", Toast.LENGTH_LONG).show()
            }else{
                registerUser(userName,password)
            }
//            val editText_firstName = findViewById<EditText>(R.id.editText_firstName).text.toString()
//            val editText_lastName = findViewById<EditText>(R.id.editText_lastName).text.toString()
//            val editText_address = findViewById<EditText>(R.id.editText_address).text.toString()
//            val editText_city = findViewById<EditText>(R.id.editText_city).text.toString()
//            val editText_postalCode = findViewById<EditText>(R.id.editText_postalCode).text.toString()
//            val editText_country = findViewById<EditText>(R.id.editText_country).text.toString()

//            if (editText_userName.isEmpty() or editText_password.isEmpty() or editText_firstName.isEmpty()
//                or editText_lastName.isEmpty() or editText_address.isEmpty() or editText_city.isEmpty()
//                or editText_postalCode.isEmpty() or editText_country.isEmpty()) {
//                Toast.makeText( context,"Please fill out all of the fields", Toast.LENGTH_LONG).show()
//                return
//            }
//            val userId = dbRef.push().key!!
//            val user = User(userId,editText_userName,editText_password,editText_firstName,
//                editText_lastName,editText_address,editText_city,editText_postalCode,editText_country
//            )
//            dbRef.child(userId).setValue(user).addOnCompleteListener{
//                Toast.makeText( context,"Success!", Toast.LENGTH_LONG).show()
//            }.addOnFailureListener{ err->
//                Toast.makeText( context,"Error ${err.message}", Toast.LENGTH_LONG).show()
//            }
        }
    }

    fun registerUser(userName:String, password:String){
        auth.createUserWithEmailAndPassword(userName, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText( context,"Success!", Toast.LENGTH_LONG).show()
                    val user = auth.currentUser
                    // back to login
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun backToLogin_pressed(view: View){
        if(view.id == R.id.btn_backToLogin){
            finish()
        }
    }
}