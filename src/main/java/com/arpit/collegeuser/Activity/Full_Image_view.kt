package com.arpit.collegeuser.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arpit.collegeuser.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_full__image_view.*

class Full_Image_view : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full__image_view)

       val FullImage= intent.getStringExtra("full_image")
        Glide.with(this).load(FullImage).into(photo_view)
    }
}