package com.example.challenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.challenge.databinding.ActivityMainBinding
import com.example.challenge.model.Ship
import com.example.challenge.utils.Status
import com.example.challenge.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.btnSky.setOnClickListener {
            mainViewModel.fetchDetails("SKY")
        }

        binding.btnBliss.setOnClickListener {
            mainViewModel.fetchDetails("BLISS")
        }

        binding.btnEscape.setOnClickListener {
                mainViewModel.fetchDetails("ESCAPE")
        }
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.shipDetails.visibility = View.VISIBLE
                    setDetailsData(it.data)
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.shipDetails.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.shipDetails.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setDetailsData(data: Ship?) {
        data?.let {
            binding.txtShipName.text = it.shipName
            binding.txtCapacity.text = it.shipFacts?.passengerCapacity
            binding.txtCrew.text = it.shipFacts?.crew
            binding.txtInauguralDate.text = it.shipFacts?.inauguralDate
        }
    }

}