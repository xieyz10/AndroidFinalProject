package com.example.firebase

import android.content.Context
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Entity.Flower
import com.example.mingyuanxie_mapd711_assignment4.ProductAdapter
import com.google.firebase.database.*


class ProductListActivity: AppCompatActivity() {
    lateinit var context: Context
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var flowerList: ArrayList<Flower>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlist)
        context = this@ProductListActivity
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("Product")
        flowerList = arrayListOf<Flower>()
//        getProductData()
    }

//    fun getProductData(){
//        dbRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for(flowerSnap in snapshot.children){
//                    val flowerData = flowerSnap.getValue(Flower::class.java)
//                    if(flowerSnap.child("flowerName").toString() == "carnation"){
//                        flowerData!!.imageId = R.drawable.carnation
//                    }else if(flowerSnap.child("flowerName").toString() == "daisy"){
//                        flowerData!!.imageId = R.drawable.daisy
//                    }else if(flowerSnap.child("flowerName").toString() == "lavender"){
//                        flowerData!!.imageId = R.drawable.lavender
//                    }
//                    else if(flowerSnap.child("flowerName").toString() == "violets"){
//                        flowerData!!.imageId = R.drawable.violets
//                    }
//                    flowerList.add(flowerData!!)
//                }
//                val arrayAdapter = ProductAdapter(this@ProductListActivity, flowerList)
//                var mListView = findViewById<ListView>(R.id.listView_product)
//                mListView.adapter = arrayAdapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//    }
}