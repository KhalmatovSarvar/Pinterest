package com.example.pinterest.activity.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R
import com.example.pinterest.activity.fragments.HomeFragment
import com.example.pinterest.activity.models.PhotoHome
import com.example.pinterest.activity.models.PhotoTest
import com.squareup.picasso.Picasso


class RecyclerHomeAdapter(var context: HomeFragment,
                          var items: ArrayList<PhotoHome>
                          ,var sendImage:(Int,PhotoHome,ImageView)->Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo_layout_test, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var image: ImageView = view.findViewById(R.id.iv_photo)
        fun bind(position: Int) {
            var photo = items[position]
            Log.d("Rasm",photo.toString())
            ViewCompat.setTransitionName(image,""+position)
//            Glide.with(context).load(photo.img).into(image)
            Picasso.get().load(photo.urls.small).into(image)
//            Glide.with(context).load(photo.img)
//                .apply(RequestOptions())
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(image)
            itemView.setOnClickListener {
                sendImage.invoke(adapterPosition,photo,image)
            }
        }
    }

}
