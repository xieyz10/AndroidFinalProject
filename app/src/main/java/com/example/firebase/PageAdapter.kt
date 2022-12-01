package com.example.firebase
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.firebase.Fragment.CartFragment
import com.example.firebase.Fragment.OrderFragment
import com.example.firebase.Fragment.ProductListFragment
import com.example.firebase.Fragment.SettingFragment

class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return ProductListFragment()
            }
            1 -> {
                return CartFragment()
            }
            2 -> {
                return OrderFragment()
            }
            else -> {
                return SettingFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Product"
            }
            1 -> {
                return "Cart"
            }
            2 -> {
                return "Order"
            }
            3 -> {
                return "Setting"
            }
        }
        return super.getPageTitle(position)
    }

}