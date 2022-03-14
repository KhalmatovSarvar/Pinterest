package com.example.pinterest.activity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest.R
import com.example.pinterest.activity.activity.MainActivity
import com.example.pinterest.activity.adapters.RecyclerHomeAdapter
import com.example.pinterest.activity.database.Retrofit.RetrofitHttp
import com.example.pinterest.activity.models.PhotoHome
import com.example.pinterest.activity.models.PhotoItem
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragmentSub(var item: PhotoHome) : Fragment() {
    lateinit var image: ImageView
    var a = 1;
    var photoHome: ArrayList<PhotoHome> = ArrayList()
    lateinit var adapter: RecyclerHomeAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sub_details, container, false)
        initViews(view)
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(view: View) {
        image = view.findViewById(R.id.iv_photo)
        val iv_sponsor: ShapeableImageView = view.findViewById(R.id.iv_sponsor)
        val sponsor_fullName: TextView = view.findViewById(R.id.tv_sponsor_fullName)
        val sponsor_followers: TextView = view.findViewById(R.id.tv_sponsor_followers)
        val tv_description: TextView = view.findViewById(R.id.tv_description)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        getPhotosViaApiRelated()
        adapter = RecyclerHomeAdapter(requireContext() as MainActivity, photoHome)

        recyclerView.adapter = adapter



        Log.d("IIIDDD", item.id.toString())
        //giving values
        Picasso.get().load(item.urls!!.small).into(image)
//        Picasso.get().load(item.user!!.profileImage.small)
//            .placeholder(ColorDrawable(Color.parseColor(item.color))).into(iv_sponsor)
        sponsor_fullName.text = item.user!!.name
        sponsor_followers.text = "${item.likes} Followers"
        if(item.description != null) {
            tv_description.text = "${item.description}"
        }

    }

    private fun getPhotosViaApiRelated() {
        RetrofitHttp.apiService.getRelatedPhotos(item.id)
            .enqueue(object : Callback<PhotoItem?> {
                override fun onResponse(call: Call<PhotoItem?>, response: Response<PhotoItem?>) {
                    photoHome = response.body()!!.results as ArrayList<PhotoHome>
                    adapter.addAll(photoHome)
                }

                override fun onFailure(call: Call<PhotoItem?>, t: Throwable) {
                    Log.d("!!!", t.message.toString())
                }
            })
    }

}