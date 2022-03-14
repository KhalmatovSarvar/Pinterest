package com.example.pinterest.activity.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest.R
import com.example.pinterest.activity.activity.MainActivity
import com.example.pinterest.activity.adapters.RecyclerHomeAdapter
import com.example.pinterest.activity.database.Retrofit.RetrofitHttp
import com.example.pinterest.activity.models.PhotoHome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        refreshAdapter()
       recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)
               if (!recyclerView.canScrollVertically(1)){
                   getPhotosViaApi()
               }
           }
       })

    }
    private fun refreshAdapter() {
         adapter = RecyclerHomeAdapter(requireActivity() as MainActivity,photoHome)
         recyclerView.adapter = adapter
    }



    private fun getPhotosViaApi() {
//        val retrofitData = RetrofitHttp.apiService.getPhotos(page, 20)
//        retrofitData.enqueue(object : Callback<ArrayList<PhotoHome>?> {
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onResponse(
//                call: Call<ArrayList<PhotoHome>?>,
//                response: Response<ArrayList<PhotoHome>?>
//            ) {
//                if (!response.isSuccessful) {
//                    Log.d("@@@", "code: " + response.code())
//                }
//                Log.d("RRR",response.body().toString())
//                adapter.addAll(response.body()!!)
//
//            }
//            override fun onFailure(call: Call<ArrayList<PhotoHome>?>, t: Throwable) {
//                Log.d("SSS", t.message.toString())
//            }
//        })
//        return photoHome
        RetrofitHttp.apiService.getPhotos(a++,20)
            .enqueue(object : Callback<ArrayList<PhotoHome>> {
                override fun onResponse(
                    call: Call<ArrayList<PhotoHome>>,
                    response: Response<ArrayList<PhotoHome>>
                ) {
                    Log.d("TTR", response.toString())
                    adapter.addAll(response.body()!!)
                }

                override fun onFailure(call: Call<ArrayList<PhotoHome>>, t: Throwable) {
                    Log.e("@@@", t.message.toString())
                }

            })
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LLL","OnAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LLL","onCreate")
        getPhotosViaApi()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("LLL","onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("LLL","onViewStateRestored")
    }
    override fun onResume() {
        super.onResume()
        getPhotosViaApi()
    }

    override fun onStart() {
        super.onStart()
        Log.d("LLL","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LLL","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LLL","onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("LLL","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LLL","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("LLL","onDetach")
    }
}