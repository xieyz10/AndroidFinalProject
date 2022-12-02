package com.example.firebase

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.firebase.Entity.Flower
import com.example.firebase.Entity.User
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
}