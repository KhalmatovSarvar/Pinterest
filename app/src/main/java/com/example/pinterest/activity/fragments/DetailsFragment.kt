package com.example.pinterest.activity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.pinterest.R
import com.example.pinterest.activity.adapters.ViewPagerAdapter
import com.example.pinterest.activity.models.PhotoHome

class DetailsFragment(var photo:ArrayList<PhotoHome>,var position:Int,var image:ImageView) : Fragment() {
    lateinit var viewPagerAdapter:ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.details_fragment_container, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPagerAdapter = ViewPagerAdapter(parentFragmentManager)
        for (i in 0 until photo.size){
            viewPagerAdapter.add(DetailsFragmentSub(photo[i]))
        }
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = position

    }
}