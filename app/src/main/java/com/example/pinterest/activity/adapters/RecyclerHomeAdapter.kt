package com.example.pinterest.activity.adapters


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest.R
import com.example.pinterest.activity.activity.MainActivity
import com.example.pinterest.activity.fragments.DetailsFragment
import com.example.pinterest.activity.models.PhotoHome
import com.squareup.picasso.Picasso


class RecyclerHomeAdapter(
    var context: MainActivity,
    var items: ArrayList<PhotoHome>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(photoList: ArrayList<PhotoHome>) {
        items.addAll(photoList)
        notifyDataSetChanged()
    }


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
        Log.d("TTT", items.size.toString())
        return items.size
    }


    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var image: ImageView = view.findViewById(R.id.iv_photo)
        fun bind(position: Int) {
            val photo = items[position]
            Log.d("Rasm", photo.toString())
            ViewCompat.setTransitionName(image, "" + position)
            Picasso.get().load(photo.urls!!.small).into(image)
            itemView.setOnClickListener {
//                val images: ArrayList<PhotoHome> = ArrayList()
//                var pos = position
//                if (position<4) {
//                    for (i in 0..7) {
//                        images.add(items[i])
//                        pos = position
//                    }
//                }else
//                    if(position > 3 && position<items.size-4){
//                        for(i in position-3..position+3){
//                            images.add(items[i])
//                            pos = 3
//
//                        }
//                    }else{
//                        for (i in position-2 until items.size) {
//                            images.add(items[i])
//                            pos = 3
//                        }
//                    }
                context.replaceFragment(DetailsFragment(items,adapterPosition, image))
            }
        }
    }

}
