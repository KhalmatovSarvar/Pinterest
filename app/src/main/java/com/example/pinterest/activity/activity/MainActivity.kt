package com.example.pinterest.activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pinterest.R
import com.example.pinterest.activity.fragments.CommentFragment
import com.example.pinterest.activity.fragments.HomeFragment
import com.example.pinterest.activity.fragments.PersonFragment
import com.example.pinterest.activity.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        //initializing variables
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        //functions
        replaceFragment(HomeFragment())
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_search -> replaceFragment(SearchFragment())
                R.id.nav_comment -> replaceFragment(CommentFragment())
                R.id.nav_person -> replaceFragment(PersonFragment())
            }
            true
        }


    }



    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.view_pager, fragment)
            commit()
        }
    }

}