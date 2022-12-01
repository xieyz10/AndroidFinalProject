package com.example.mingyuanxie_mapd711_assignment4

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.firebase.Entity.Cart
import com.example.firebase.Entity.Order
import com.example.firebase.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CartAdapter(private val context: Activity, private val arrayList:
ArrayList<Cart>):ArrayAdapter<Cart>(context, R.layout.cartlist_item,arrayList) {
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef_order: DatabaseReference
    private lateinit var dbRef_cart: DatabaseReference
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.cartlist_item,null)
        database = FirebaseDatabase.getInstance()
        dbRef_order = database.getReference("orders")
        dbRef_cart = database.getReference("carts")

        val imageView = view.findViewById<ImageView>(R.id.imageView_flower)
        val flowerName = view.findViewById<TextView>(R.id.textView_flowerName)
        val orderDate = view.findViewById<TextView>(R.id.textView_date)
        val quantity = view.findViewById<EditText>(R.id.editText_quantity)
        val totalCost = view.findViewById<TextView>(R.id.textView_totalcost)
        val btn_placeOrder = view.findViewById<Button>(R.id.btn_cart_placeOrder_submit)
        val btn_deleteItem = view.findViewById<Button>(R.id.btn_cart_deleteItem_submit)
        imageView.setImageResource(arrayList[position].imageId)
        flowerName.text = arrayList[position].flowerName
        orderDate.text = arrayList[position].orderDate
        quantity.setText(arrayList[position].quantity.toString(), TextView.BufferType.EDITABLE);
        totalCost.text = "$"+arrayList[position].cost.toString()

        btn_placeOrder.setOnClickListener{
            val orderId = dbRef_order.push().key!!
            val order = Order(orderId,arrayList[position].userId,arrayList[position].flowerId,
                arrayList[position].flowerName,arrayList[position].quantity,
                arrayList[position].cost,arrayList[position].orderDate,"Placed",
                arrayList[position].imageId
            )
            dbRef_order.child(orderId).setValue(order).addOnCompleteListener{
                //btn_placeOrder.text = "Placed"
                dbRef_cart.child(arrayList[position].cartId).removeValue()
                Toast.makeText( context,arrayList[position].cartId, Toast.LENGTH_LONG).show()

            }.addOnFailureListener{ err->
                Toast.makeText( context,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
        }

        btn_deleteItem.setOnClickListener{
            Toast.makeText( context,"Your order has been canceled", Toast.LENGTH_LONG).show()
            dbRef_cart.child(arrayList[position].cartId).removeValue()
        }

        return view
    }
}