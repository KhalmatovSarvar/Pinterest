package com.example.pinterest.activity.activity

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.pinterest.R
import com.example.pinterest.activity.models.PhotoHome
import com.example.pinterest.activity.models.PhotoTest
import com.example.pinterest.activity.utils.setOnDoubleClickListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.reflect.Type
import java.util.*


class DetailsPhotoActivity : AppCompatActivity() {


    lateinit var imageView: ImageView
    lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_photo)

        supportPostponeEnterTransition()
        val extras = intent.extras
        val photoItem: PhotoHome = extras!!.getSerializable("photoTester") as PhotoHome

        Log.d("@@@",photoItem.toString())

        imageView = findViewById(R.id.iv_detailed_photo)
        lottieAnimationView = findViewById(R.id.lottie_animation)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName =
                extras.getString("transitionName")
            imageView.transitionName = imageTransitionName
        }

        Picasso.get().load(photoItem.urls.small).into(imageView, object : Callback {
            override fun onSuccess() {
                supportStartPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                supportStartPostponedEnterTransition();
            }
        })
        imageView.setOnDoubleClickListener {
            lottieAnimationView.setAnimation("like_animation.json")
            lottieAnimationView.playAnimation()
            object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    lottieAnimationView.visibility = View.GONE
                    Toast.makeText(
                        this@DetailsPhotoActivity,
                        "Image saved to Favourites",
                        Toast.LENGTH_SHORT
                    ).show()
                    lottieAnimationView.pauseAnimation()
                }
            }.start()


        }
    }
}