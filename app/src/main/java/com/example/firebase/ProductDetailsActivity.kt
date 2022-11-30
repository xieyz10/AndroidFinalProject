package com.example.firebase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Entity.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*

class ProductDetailsActivity: AppCompatActivity() {
    var flowerId:String? = ""
    var flowerName:String? = ""
    var flowerPrice:String?= ""
    var imageId:String?= ""
    var userId:String?= ""
    lateinit var context: Context
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this@ProductDetailsActivity
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("Orders")
        setUpValue()
        setContentView(R.layout.activity_productdetails)
        setUpProductDetail()
    }

    fun setUpValue(){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        flowerId = sharedPref.getString("flowerId", "")
        flowerName = sharedPref.getString("flowerName", "")
        flowerPrice = sharedPref.getString("flowerPrice", "")
        imageId = sharedPref.getString("imageId", "")
        userId = sharedPref.getString("userId", "")
    }

    fun setUpProductDetail(){

        if(flowerName == "violets"){
            findViewById<ImageView>(R.id.imageView_flower).setImageResource(R.drawable.violets)
        }else if(flowerName == "lavender"){
            findViewById<ImageView>(R.id.imageView_flower).setImageResource(R.drawable.lavender)
        }else if(flowerName == "daisy"){
            findViewById<ImageView>(R.id.imageView_flower).setImageResource(R.drawable.daisy)
        } else if(flowerName == "carnation"){
            findViewById<ImageView>(R.id.imageView_flower).setImageResource(R.drawable.carnation)
        }
        findViewById<TextView>(R.id.textView_flowerName).text = flowerName
        findViewById<TextView>(R.id.textView_flowerPrice).text = flowerPrice
    }

    fun btnMinus_pressed(view: View){
        if(view.id == R.id.btn_deduct){
            val editText_productCount = findViewById<EditText>(R.id.editText_productCount)
            var count = editText_productCount.text.toString().toInt()
            if(count > 0){
                count-=1
                editText_productCount.setText(count.toString(), TextView.BufferType.EDITABLE);
            }
        }
    }

    fun btnAdd_pressed(view: View){
        if(view.id == R.id.btn_plus){
            val editText_productCount = findViewById<EditText>(R.id.editText_productCount)
            var count = editText_productCount.text.toString().toInt()
            count+=1
            editText_productCount.setText(count.toString(), TextView.BufferType.EDITABLE);
        }
    }


    fun btnAddToCart_pressed(view:View){
        if(view.id == R.id.btn_addToCart){
            var quantity = findViewById<EditText>(R.id.editText_productCount).text.toString().toInt()
            val cost:Double = quantity * flowerPrice!!.substring(1)!!.toDouble()
            val currentTime: Date = Calendar.getInstance().getTime()
            val orderDate = currentTime.toString()

            val orderId = dbRef.push().key!!
            val order = Order(orderId,userId!!,flowerId!!,flowerName!!,quantity,cost,orderDate,"Ready To Check Out",imageId!!.toInt())
            dbRef.child(orderId).setValue(order).addOnCompleteListener{
                Toast.makeText( context,"The item has successfully been added to the cart!", Toast.LENGTH_LONG).show()
                var intent = Intent(this@ProductDetailsActivity, HomeActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{ err->
                Toast.makeText( context,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun btnPlaceOrder_pressed(view:View){
        if(view.id == R.id.btn_placeOrder){

        }
    }
}