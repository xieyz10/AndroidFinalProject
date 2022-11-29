package com.example.firebase

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Entity.Flower
import com.google.firebase.database.FirebaseDatabase

class ProductDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetails)
        setUpProductDetail()
    }

    fun setUpProductDetail(){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        val flowerId = sharedPref.getString("flowerId", "")
        val flowerName = sharedPref.getString("flowerName", "")
        val flowerPrice = sharedPref.getString("flowerPrice", "")
        val imageId = sharedPref.getString("imageId", "")
        Toast.makeText( this,imageId.toString(), Toast.LENGTH_LONG).show()
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

}