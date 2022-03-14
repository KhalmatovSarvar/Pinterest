package com.example.pinterest.activity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager?) :
    FragmentStatePagerAdapter(fm!!) {
    private var fragments: ArrayList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    fun add(fragment: Fragment) {
        fragments.add(fragment)
    }
    override fun getCount(): Int {
        return fragments.size
    }
}