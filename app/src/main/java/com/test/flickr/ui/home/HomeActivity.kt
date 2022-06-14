package com.test.flickr.ui.home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.test.flickr.databinding.ActivityMainBinding
import com.test.flickr.utils.PhotoBuilder.getPhotoUrl
import com.test.flickr.ui.home.adapter.PhotoAdapter
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
            homeViewModel.getPhotos()
        }

        homeViewModel.photosResponse.observe(this, Observer {
            binding.moviesRv.visibility = View.VISIBLE
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
            val bundle =
                bundleOf("image" to getPhotoUrl(it.farm, it.server, it.id.toString(), it.secret))
        }
        val layoutManager = GridLayoutManager(applicationContext, 2)
        binding.moviesRv.layoutManager = layoutManager
        binding.moviesRv.adapter = adapter

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
                        homeViewModel.getPhotos(it)
                    }
                }
                return true
            }
        })

    }

    private fun hidePhotosList() {
        binding.moviesRv.visibility = View.GONE
    }

    private fun showPhotosList() {
        binding.moviesRv.visibility = View.VISIBLE
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