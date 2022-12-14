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
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Entity.Cart
import com.example.firebase.Entity.Flower
import com.example.firebase.Entity.Order
import com.example.firebase.R
import com.example.mingyuanxie_mapd711_assignment4.CartAdapter
import com.example.mingyuanxie_mapd711_assignment4.ProductAdapter
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
class CartFragment : Fragment() {
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var cartList: ArrayList<Cart>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("carts")

        var contentView: View= inflater.inflate(R.layout.fragment_cart, container, false)
        val listView = contentView.findViewById<ListView>(R.id.listView_cart)
        val emptyImage= contentView.findViewById<View>(R.id.imageView_emptyCart)
        val emptyText= contentView.findViewById<View>(R.id.textView_cartEmpty)
        setListView(listView, emptyImage, emptyText)
        //setupListItemClickEvent(contentView)
        return contentView
    }

    fun setListView(listView:ListView, emptyImage:View, emptyText:View){
        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        val userId = sharedPref.getString("userId","")
        dbRef.orderByChild("userId").equalTo(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cartList = arrayListOf<Cart>()
                for(cartSnap in snapshot.children){
                    val cartData = cartSnap.getValue()
                    val cart = cartData as HashMap<String, Any>
                    val cartId = cart["cartId"].toString()
                    val userId = cart["userId"].toString()
                    val flowerId = cart["flowerId"].toString()
                    val flowerName = cart["flowerName"].toString()
                    val quantity = cart["quantity"].toString().toInt()
                    val cost = cart["cost"].toString().toDouble()
                    val orderDate = cart["orderDate"].toString()
                    val status = cart["status"].toString()
                    val imageId = cart["imageId"].toString().toInt()
                    val cartObj = Cart(cartId, userId, flowerId, flowerName,quantity,cost,orderDate,status,imageId)
                    if(cartObj.flowerName == "Carnation"){
                        cartObj.imageId = com.example.firebase.R.drawable.carnation
                    }else if(cartObj.flowerName == "Daisy"){
                        cartObj.imageId = com.example.firebase.R.drawable.daisy
                    }else if(cartObj.flowerName == "Lavender"){
                        cartObj.imageId = com.example.firebase.R.drawable.lavender
                    }else if(cartObj.flowerName == "Violets"){
                        cartObj.imageId = com.example.firebase.R.drawable.violets
                    }else if(cartObj.flowerName == "Jasmine"){
                        cartObj.imageId = com.example.firebase.R.drawable.jasmine
                    }
                    cartList.add(cartObj)
                }
                // check cartList length is not zero
                if(cartList.size == 0){
                    // find the empty view by id
                    emptyImage.visibility = View.VISIBLE
                    emptyText.visibility = View.VISIBLE
                     //
                    // set listview visibility to gone
                    listView.visibility = View.GONE

                }else{
                    // show listview
                    listView.visibility = View.VISIBLE
                    // hide empty view
                    emptyImage.visibility = View.GONE
                    emptyText.visibility = View.GONE
                }
                val listAdapter = CartAdapter(activity!!,cartList)
                listView.adapter = listAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}