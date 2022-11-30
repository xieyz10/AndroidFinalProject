package com.example.firebase

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductDetailsActivity: AppCompatActivity() {

    var flowerId:String? = ""
    var flowerName:String? = ""
    var flowerPrice:String?= ""
    var imageId:String?= ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        }
    }

    fun btnPlaceOrder_pressed(view:View){
        if(view.id == R.id.btn_placeOrder){

        }
    }
}