package com.example.firebase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
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
        dbRef = database.getReference("products")
        flowerList = arrayListOf<Flower>()
        getProductData()
        setupListItemClickEvent()
    }

    fun getProductData(){
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(flowerSnap in snapshot.children){
                    val flowerData = flowerSnap.getValue()
                    val flower = flowerData as HashMap<String, Any>
                    val flowerId = flower["flowerId"].toString()
                    val flowerName = flower["flowerName"].toString()
                    val flowerPrice = flower["flowerPrice"].toString()
                    val imageId = flower["imageId"].toString().toInt()
                    val flowerObj = Flower(flowerId, flowerName, flowerPrice, imageId)
                    if(flowerObj.flowerName == "carnation"){
                        flowerObj.imageId = R.drawable.carnation
                    }else if(flowerObj.flowerName == "daisy"){
                        flowerObj.imageId = R.drawable.daisy
                    }else if(flowerObj.flowerName == "lavender"){
                        flowerObj.imageId = R.drawable.lavender
                    }
                    else if(flowerObj.flowerName == "violets"){
                        flowerObj.imageId = R.drawable.violets
                    }
                    flowerList.add(flowerObj)
                }
                val arrayAdapter = ProductAdapter(this@ProductListActivity, flowerList)
                var mListView = findViewById<ListView>(R.id.listView_product)
                mListView.adapter = arrayAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun setupListItemClickEvent(){
        var mListView = findViewById<ListView>(R.id.listView_product)
        val intent = Intent(this@ProductListActivity, ProductDetailsActivity::class.java)
        mListView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putString("flowerId",flowerList[position].flowerId.toString())
            editor.putString("flowerName",flowerList[position].flowerName.toString())
            editor.putString("flowerPrice",flowerList[position].flowerPrice.toString())
            editor.putString("imageId",flowerList[position].imageId.toString())
            editor.commit()
            startActivity(intent)
        })
    }

}