package com.example.mingyuanxie_mapd711_assignment4

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.firebase.Entity.Flower
import com.example.firebase.R

class ProductAdapter(private val context: Activity, private val arrayList:
ArrayList<Flower>):ArrayAdapter<Flower>(context, R.layout.productlist_item,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.productlist_item,null)

        val imageView = view.findViewById<ImageView>(R.id.imageView_flower)
        val flowerName = view.findViewById<TextView>(R.id.textView_flowerName)
        val flowerPrice = view.findViewById<TextView>(R.id.textView_flowerPrice)

        imageView.setImageResource(arrayList[position].imageId)
        flowerName.text = arrayList[position].flowerName
        flowerPrice.text = arrayList[position].flowerPrice

        return view
    }
}