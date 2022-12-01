package com.example.mingyuanxie_mapd711_assignment4

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.example.firebase.Entity.Flower
import com.example.firebase.Entity.Order
import com.example.firebase.R

class OrderAdapter(private val context: FragmentActivity, private val arrayList:
ArrayList<Order>):ArrayAdapter<Order>(context, R.layout.orderlist_item,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.orderlist_item,null)

        val imageView = view.findViewById<ImageView>(R.id.imageView_flower)
        val flowerName = view.findViewById<TextView>(R.id.textView_flowerName)
        val orderDate = view.findViewById<TextView>(R.id.textView_date)
        val quantity = view.findViewById<EditText>(R.id.editText_quantity)
        val totalCost = view.findViewById<TextView>(R.id.textView_totalcost)

        imageView.setImageResource(arrayList[position].imageId)
        flowerName.text = arrayList[position].flowerName
        orderDate.text = arrayList[position].orderDate
        quantity.setText(arrayList[position].quantity.toString(), TextView.BufferType.EDITABLE);
        totalCost.text = "$"+arrayList[position].cost.toString()
        return view
    }
}