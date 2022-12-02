package com.example.firebase.Fragment

import android.R
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.firebase.Entity.Flower
import com.example.firebase.ProductDetailsActivity
import com.example.mingyuanxie_mapd711_assignment4.ProductAdapter
import com.google.firebase.database.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var flowerList: ArrayList<Flower>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("products")

        var contentView: View= inflater.inflate(com.example.firebase.R.layout.fragment_product_list, container, false)
        val listView = contentView.findViewById<ListView>(com.example.firebase.R.id.listView_product)
        setListView(listView)
        setupListItemClickEvent(contentView)
        return contentView
    }

    fun setListView(listView:ListView){
        flowerList = arrayListOf<Flower>()
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(flowerSnap in snapshot.children){
                    val flowerData = flowerSnap.getValue()
                    val flower = flowerData as HashMap<String, Any>
                    val flowerId = flower["flowerId"].toString()
                    val flowerName = flower["flowerName"].toString()
                    val flowerPrice = flower["flowerPrice"].toString()
                    val imageId = flower["imageId"].toString().toInt()
                    val flowerObj = Flower(flowerId, flowerName, flowerPrice, imageId)
                    if(flowerObj.flowerName == "Carnation"){
                        flowerObj.imageId = com.example.firebase.R.drawable.carnation
                    }else if(flowerObj.flowerName == "Daisy"){
                        flowerObj.imageId = com.example.firebase.R.drawable.daisy
                    }else if(flowerObj.flowerName == "Lavender"){
                        flowerObj.imageId = com.example.firebase.R.drawable.lavender
                    }else if(flowerObj.flowerName == "Violets"){
                        flowerObj.imageId = com.example.firebase.R.drawable.violets
                    }else{
                        flowerObj.imageId = com.example.firebase.R.drawable.jasmine
                    }
                    flowerList.add(flowerObj)
                }
                val listAdapter = ProductAdapter(activity!!,flowerList)
                listView.adapter = listAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun setupListItemClickEvent(contentView:View){
        val listView = contentView.findViewById<ListView>(com.example.firebase.R.id.listView_product)
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        listView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("MyPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putString("flowerId",flowerList[position].flowerId.toString())
            editor.putString("flowerName",flowerList[position].flowerName.toString())
            editor.putString("flowerPrice",flowerList[position].flowerPrice.toString())
            editor.putString("imageId",flowerList[position].imageId.toString())
            editor.commit()
            startActivity(intent)

//            val fragment: Fragment = ProductDetailsFragment()
//            val fragmentManager: FragmentManager? = fragmentManager
//            fragmentManager!!.beginTransaction().replace(com.example.firebase.R.id.frameLayout_productList, fragment).commit()
        })
    }

}