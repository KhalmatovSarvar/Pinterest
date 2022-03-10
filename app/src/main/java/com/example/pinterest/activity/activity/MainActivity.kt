package com.example.pinterest.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.pinterest.R
import com.example.pinterest.activity.adapters.ViewPagerAdapter
import com.example.pinterest.activity.fragments.CommentFragment
import com.example.pinterest.activity.fragments.HomeFragment
import com.example.pinterest.activity.fragments.PersonFragment
import com.example.pinterest.activity.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    (HomeFragment())
                    viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_search -> {
                    (SearchFragment())
                    viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_comment -> {
                    (CommentFragment())
                    viewPager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_person -> {
                    (PersonFragment())
                    viewPager.currentItem = 3
                    return@OnNavigationItemSelectedListener true
                }
                else -> viewPager.currentItem = 0
            }
            false
        }

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        //initializing variables
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        viewPager = findViewById(R.id.view_pager)

        //functions
        implementBottomNavigation()


    }

    private fun implementBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigationView.selectedItemId = R.id.nav_home


        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.add(HomeFragment(), "HomeFragment")
        viewPagerAdapter.add(SearchFragment(), "SearchFragment")
        viewPagerAdapter.add(CommentFragment(), "CommentFragment")
        viewPagerAdapter.add(PersonFragment(), "PersonFragment")


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        viewPager.adapter = viewPagerAdapter
    }

}