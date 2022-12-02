package com.example.firebase.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.example.firebase.Entity.Cart
import com.example.firebase.Entity.Flower
import com.example.firebase.Entity.Order
import com.example.firebase.R
import com.example.mingyuanxie_mapd711_assignment4.CartAdapter
import com.example.mingyuanxie_mapd711_assignment4.OrderAdapter
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var orderList: ArrayList<Order>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("orders")
        orderList = arrayListOf<Order>()
        var contentView: View= inflater.inflate(R.layout.fragment_order, container, false)
        val listView = contentView.findViewById<ListView>(R.id.listView_order)
        setListView(listView)
        //setupListItemClickEvent(contentView)
        return contentView
    }

    fun setListView(listView:ListView){
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        val userId = sharedPref.getString("userId","")
        dbRef.orderByChild("userId").equalTo(userId).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                orderList = arrayListOf<Order>()
                for(orderSnap in snapshot.children){
                    val orderData = orderSnap.getValue()
                    val order = orderData as HashMap<String, Any>
                    val orderId = order["orderId"].toString()
                    val userId = order["userId"].toString()
                    val flowerId = order["flowerId"].toString()
                    val flowerName = order["flowerName"].toString()
                    val quantity = order["quantity"].toString().toInt()
                    val cost = order["cost"].toString().toDouble()
                    val orderDate = order["orderDate"].toString()
                    val status = order["status"].toString()
                    val imageId = order["imageId"].toString().toInt()
                    val orderObj = Order(orderId, userId, flowerId, flowerName,quantity,cost,orderDate,status,imageId)
                    if(orderObj.flowerName == "Carnation"){
                        orderObj.imageId = com.example.firebase.R.drawable.carnation
                    }else if(orderObj.flowerName == "Daisy"){
                        orderObj.imageId = com.example.firebase.R.drawable.daisy
                    }else if(orderObj.flowerName == "Lavender"){
                        orderObj.imageId = com.example.firebase.R.drawable.lavender
                    }else if(orderObj.flowerName == "Violets"){
                        orderObj.imageId = com.example.firebase.R.drawable.violets
                    }else if(orderObj.flowerName == "Jasmine"){
                        orderObj.imageId = com.example.firebase.R.drawable.jasmine
                    }
                    orderList.add(orderObj)
                }
                val listAdapter = OrderAdapter(activity!!,orderList)
                listView.adapter = listAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}