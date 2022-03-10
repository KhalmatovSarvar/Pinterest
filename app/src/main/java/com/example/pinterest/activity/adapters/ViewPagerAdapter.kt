package com.example.pinterest.activity.adapters

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter


class ViewPagerAdapter(fm: FragmentManager?) :
    FragmentStatePagerAdapter(fm!!) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val fragmentTitle: MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    fun add(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}
