package com.example.firebase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Entity.Flower
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("Product")
        auth = Firebase.auth
    }

    fun btnLogin_pressed(view: View){
        if(view.id == R.id.btn_login_submit){
            val userName = findViewById<EditText>(R.id.editText_username).text.toString()
            val password = findViewById<EditText>(R.id.editText_password).text.toString()
            auth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText( context,"Login Success!", Toast.LENGTH_LONG).show()
                        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPref.edit()
                        editor.putString("userId",user?.uid.toString())
                        var intent = Intent(this@MainActivity, ProductListActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
//            dbRef.child("User").child(userName).get().addOnSuccessListener {
//                Toast.makeText( context,"${it.value}", Toast.LENGTH_LONG).show()
//                Log.i("firebase", "Got value ${it.value}")
//            }.addOnFailureListener{
//                Log.e("firebase", "Error getting data", it)
//                Toast.makeText( context,"in???", Toast.LENGTH_LONG).show()
//            }
        }
    }

    fun btnRegister_pressed(view: View){
        if(view.id == R.id.btn_navigateToRegisterPage){
            var intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun btnAddProduct_pressed(view: View){
        if(view.id == R.id.btn_addProduct){
            val productId1 = dbRef.push().key!!
            val product1 = Flower(productId1,"violets","$8.99",1)
            dbRef.child(productId1).setValue(product1)
            val productId2 = dbRef.push().key!!
            val product2 = Flower(productId2,"lavender","$11.99",2)
            dbRef.child(productId2).setValue(product2)
            val productId3 = dbRef.push().key!!
            val product3 = Flower(productId3,"daisy","$7.99",3)
            dbRef.child(productId3).setValue(product3)
            val productId4 = dbRef.push().key!!
            val product4 = Flower(productId4,"carnation","$12.99",4)
            dbRef.child(productId4).setValue(product4)
            Toast.makeText( context,"Success!", Toast.LENGTH_LONG).show()
        }
    }

}