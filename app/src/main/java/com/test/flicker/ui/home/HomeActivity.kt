package com.test.flicker.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.flicker.databinding.ActivityMainBinding
import com.test.flicker.utils.PhotoBuilder.getPhotoUrl
import com.test.photoapp.platform.view.adapter.PhotoAdapter
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