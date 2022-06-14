package com.test.flickr.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.test.flickr.R
import com.test.flickr.databinding.ActivityImageFullSizeBinding
import com.test.flickr.model.Photo
import com.test.flickr.utils.PhotoBuilder

class ImageFullSizeActivity : AppCompatActivity() {
    lateinit var binding: ActivityImageFullSizeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageFullSizeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .load(
                PhotoBuilder.getPhotoUrl(
                    intent.getIntExtra("farm",0),
                    intent.getStringExtra("server")?:"",
                    intent.getStringExtra("id")?:"",
                    intent.getStringExtra("secret")?:""
                )
            )
            .timeout(60000)
            .placeholder(R.drawable.ic_photo)
            .error(R.drawable.ic_photo)
            .into(binding.image)

    }
}