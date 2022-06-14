package com.test.flickr.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.test.flickr.R
import com.test.flickr.databinding.ItemPhotoBinding
import com.test.flickr.model.Photo
import com.test.flickr.utils.PhotoBuilder.getPhotoUrl
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class PhotoAdapter(private val listener: (photo: Photo) -> Unit) :
    ListAdapter<Photo, PhotoAdapter.ViewHolder>(diffCallback) {

    inner class ViewHolder(
        private val context: Context,
        private val binding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo?) {
            val imageUrl = photo?.let {
                getPhotoUrl(
                    it.farm,
                    photo.server,
                    photo.id,
                    photo.secret
                )
            }

            binding.photo.setOnClickListener {
                listener.invoke(photo!!)
            }
            photo?.let {


//                binding.photo.load( getPhotoUrl(
//                    photo.farm,
//                    photo.server,
//                    photo.id,
//                    photo.secret
//                )){
//                    placeholder(R.drawable.ic_photo)
//                    error(R.drawable.brokenimage)
//                }
//                    Glide.with(context)
//                        .load(
//                            getPhotoUrl(
//                                photo.farm,
//                                photo.server,
//                                photo.id,
//                                photo.secret
//                            )
//                        )
//                        .timeout(600000)
//                        .placeholder(R.drawable.ic_photo)
//                        .error(R.drawable.ic_photo)
//                    .into(binding.photo);
//                val picasso = Picasso.Builder(context)
//                    .listener { _, _, e ->
//                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//                        e.printStackTrace()
//                    }
//                    .build()
//                picasso
//                    .load(
//                        getPhotoUrl(
//                            photo.farm,
//                            photo.server,
//                            photo.id,
//                            photo.secret
//                        )
//                    )
//                    .error(R.drawable.ic_photo)
//                    .into(binding.photo)

//                val okHttpClient = OkHttpClient()
//                val okHttp3Downloader = OkHttp3Downloader(okHttpClient)
//                val picasso = Picasso.Builder(context)
//                    .downloader(okHttp3Downloader)
//                    .build()
                Picasso.get()
                    .load("https://farm66.staticflickr.com/65535/52146372110_2f79497603_w.jpg")
                    .resize(200, 200)
                    .placeholder(R.drawable.ic_photo)
                    .error(R.drawable.ic_photo)
                    .into(binding.photo, object : Callback {
                        override fun onSuccess() {
                        }

                        override fun onError(e: Exception?) {
                            e?.printStackTrace()
                        }

                    })


                binding.photoTitle.text = photo.title
                binding.photoDescription.text = photo.description._content
                binding.authorName.text = photo.ownername

                val originalFormat: DateFormat =
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                val targetFormat: DateFormat = SimpleDateFormat("dd,MMMM,yyyy", Locale.ENGLISH)
                val date: Date = originalFormat.parse(photo.datetaken)
                val formattedDate: String = targetFormat.format(date)
                binding.photoDate.text = formattedDate
            }
        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPhotoBinding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(parent.context, binding)
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(
                oldItem: Photo,
                newItem: Photo
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Photo,
                newItem: Photo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}