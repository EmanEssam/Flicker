package com.test.photoapp.platform.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.flicker.R
import com.test.flicker.databinding.ItemPhotoBinding
import com.test.flicker.model.Photo
import com.test.flicker.utils.PhotoBuilder.getPhotoUrl

class PhotoAdapter(private val listener: (photo: Photo) -> Unit) :
    ListAdapter<Photo, PhotoAdapter.ViewHolder>(diffCallback) {

    inner class ViewHolder(
        private val context: Context,
        private val binding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo?) {
            binding.photo.setOnClickListener {
                listener.invoke(photo!!)
            }
            photo?.let {
                val picasso = Picasso.Builder(context)
                    .listener { _, _, e ->
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                    .build()
                picasso
                    .load(
                        getPhotoUrl(
                            photo.farm,
                            photo.server,
                            photo.id,
                            photo.secret
                        )
                    )
                    .error(R.drawable.ic_photo)
                    .into(binding.photo)

                binding.photoTitle.text = photo.title
                binding.photoDescription.text = photo.title
                binding.authorName.text = photo.owner
                binding.photoDate.text = photo.title

            }
        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
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