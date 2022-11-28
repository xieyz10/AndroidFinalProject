package com.example.firebase

import android.content.Context
import android.os.Bundle
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

    fun insertProducts(){
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