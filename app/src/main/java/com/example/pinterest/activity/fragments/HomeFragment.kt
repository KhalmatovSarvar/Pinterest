package com.example.pinterest.activity.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest.R
import com.example.pinterest.activity.activity.DetailsPhotoActivity
import com.example.pinterest.activity.adapters.RecyclerHomeAdapter
import com.example.pinterest.activity.database.Retrofit.RetrofitHttp
import com.example.pinterest.activity.models.PhotoHome
import com.example.pinterest.activity.models.PhotoTest
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

class HomeFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    var photoHome: ArrayList<PhotoHome> = ArrayList()
    lateinit var adapter: RecyclerHomeAdapter
    var a = 1;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        initViews(view)
        Log.d("Life","onCreateView")
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recycler_home)
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager =layoutManager
       getPhotosViaApi(a)
       recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)
               val into = intArrayOf(0,0)
               val lastVisiblePosition = layoutManager.findLastVisibleItemPositions(into)
               if(lastVisiblePosition[1] == layoutManager.itemCount-1||lastVisiblePosition[0] == layoutManager.itemCount-1)
                   getPhotosViaApi(++a)
           }
       })

    }



    private fun sendPhotoToActivity(pos: Int, photo: PhotoHome, image: ImageView) {
        val intent = Intent(context, DetailsPhotoActivity::class.java)
        intent.putExtra("photoTester", photo)
        intent.putExtra("transitionName", ViewCompat.getTransitionName(image))
        val options = ActivityOptions.makeSceneTransitionAnimation(
            context as Activity?,
            image,
            ViewCompat.getTransitionName(image)
        )
        startActivity(intent, options.toBundle())
    }

    private fun refreshAdapter(photos: ArrayList<PhotoHome>) {
         adapter = RecyclerHomeAdapter(this, photos) { pos, photo, image ->
            sendPhotoToActivity(pos, photo, image)
        }
        recyclerView.adapter = adapter

    }



    private fun getPhotosViaApi(page:Int): List<PhotoHome> {

        var retrofitData = RetrofitHttp.apiService.getPhotos(page, 20)
        retrofitData.enqueue(object : Callback<ArrayList<PhotoHome>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ArrayList<PhotoHome>?>,
                response: Response<ArrayList<PhotoHome>?>
            ) {
                if (!response.isSuccessful) {
                    Log.d("@@@", "code: " + response.code())
                }
//                photoHome.clear()
                if (a==1){
                    refreshAdapter(response.body()!!)
                }else{
                    adapter.items.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                }
//                photoHome.addAll(response.body() as ArrayList<PhotoHome>)
//                 refreshAdapter(photoHome)
            }

            override fun onFailure(call: Call<ArrayList<PhotoHome>?>, t: Throwable) {
                Log.d("@@@", t.message.toString())
            }
        })
        return photoHome

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Life","OnAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Life","onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("Life","onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Life","onViewStateRestored")
    }
    override fun onResume() {
        super.onResume()

        //  getPhotosViaApi()
//        refreshAdapter(getPhotosViaApi() as ArrayList<PhotoHome>)
        Log.d("RRR", "${photoHome.size}")
    }

    override fun onStart() {
        super.onStart()
//        refreshAdapter(getPhotosViaApi() as ArrayList<PhotoHome>)
//        adapter.items.clear()
//        adapter.items.addAll(photoHome)
  //      adapter.notifyDataSetChanged()
        Log.d("Life","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Life","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Life","onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Life","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Life","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Life","onDetach")
    }
}