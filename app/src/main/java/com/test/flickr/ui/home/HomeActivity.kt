package com.test.flickr.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.flickr.R
import com.test.flickr.databinding.ActivityMainBinding
import com.test.flickr.model.Photo
import com.test.flickr.ui.home.adapter.PhotoAdapter
import com.test.flickr.utils.PhotoBuilder.getPhotoUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PhotoAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setupRecyclerView()
        setUpObserver()
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            homeViewModel.getPhotos(apiKey = getString(R.string.api_key))
        }
        homeViewModel.photosResponse.observe(this, Observer {
            binding.photosRV.visibility = View.VISIBLE
            adapter.submitList(emptyList())
            adapter.submitList(it)
            showPhotosList()
            hideProgressDialog()
        })

        homeViewModel.photosError.observe(this, Observer {
            showLongToast(it)
        })
    }

    private fun setupRecyclerView() {
        adapter = PhotoAdapter {
            openFullSizeScreen(it)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.photosRV.layoutManager = layoutManager
        binding.photosRV.adapter = adapter

    }

    private fun openFullSizeScreen(photo: Photo) {
        val intent = Intent(this, ImageFullSizeActivity::class.java)
        intent.putExtra("id", photo.id)
        intent.putExtra("secret", photo.secret)
        intent.putExtra("server", photo.server)
        intent.putExtra("farm", photo.farm)

        startActivity(intent)
    }


    private fun initViews() {
        binding.searchEt.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty() && it.isNotBlank()) {
                        showProgressDialog()
                        hidePhotosList()
                        homeViewModel.getPhotos(
                            apiKey = getString(R.string.api_key),
                            searchKey = it
                        )
                    }
                }
                return true
            }
        })

    }

    private fun hidePhotosList() {
        binding.photosRV.visibility = View.GONE
    }

    private fun showPhotosList() {
        binding.photosRV.visibility = View.VISIBLE
    }

    private fun hideProgressDialog() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressDialog() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showLongToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}