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
    fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = manager.beginTransaction()
            ft.replace(R.id.container, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}