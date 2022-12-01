package com.example.firebase

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.firebase.Entity.Flower
import com.example.firebase.Fragment.CartFragment
import com.example.firebase.Fragment.OrderFragment
import com.example.firebase.Fragment.ProductListFragment
import com.example.firebase.Fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.*


class HomeActivity: AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        context = this@HomeActivity
        //setBottomNavigation()
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = PageAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

    }

//    fun setBottomNavigation(){
//        val productListFragment=ProductListFragment()
//        val orderFragment=OrderFragment()
//        val settingFragment=SettingFragment()
//        val cartFragment= CartFragment()
//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        bottomNavigationView.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.home->setCurrentFragment(productListFragment)
//                R.id.cart->setCurrentFragment(cartFragment)
//                R.id.orders->setCurrentFragment(orderFragment)
//                R.id.settings->setCurrentFragment(settingFragment)
//            }
//            true
//        }
//
//        setCurrentFragment(productListFragment)
//    }

//    private fun setCurrentFragment(fragment: Fragment)=
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragment,fragment)
//            commit()
//        }

//    fun getProductData(){
//        dbRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for(flowerSnap in snapshot.children){
//                    val flowerData = flowerSnap.getValue()
//                    val flower = flowerData as HashMap<String, Any>
//                    val flowerId = flower["flowerId"].toString()
//                    val flowerName = flower["flowerName"].toString()
//                    val flowerPrice = flower["flowerPrice"].toString()
//                    val imageId = flower["imageId"].toString().toInt()
//                    val flowerObj = Flower(flowerId, flowerName, flowerPrice, imageId)
//                    if(flowerObj.flowerName == "carnation"){
//                        flowerObj.imageId = R.drawable.carnation
//                    }else if(flowerObj.flowerName == "daisy"){
//                        flowerObj.imageId = R.drawable.daisy
//                    }else if(flowerObj.flowerName == "lavender"){
//                        flowerObj.imageId = R.drawable.lavender
//                    }
//                    else if(flowerObj.flowerName == "violets"){
//                        flowerObj.imageId = R.drawable.violets
//                    }
//                    flowerList.add(flowerObj)
//                }
//                val arrayAdapter = ProductAdapter(this@ProductListActivity, flowerList)
//                var mListView = findViewById<ListView>(R.id.listView_product)
//                mListView.adapter = arrayAdapter
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText( context,"${error.message}", Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }

}