package com.example.androidtraining20220719.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.androidtraining20220719.R
import com.example.androidtraining20220719.databinding.ActivityMainBinding
import com.example.androidtraining20220719.view_model.TopPageViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: TopPageViewModel by viewModels()
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCharactersData()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.vm = viewModel


        val navController = findNavController(R.id.nav_host_fragment)
        setupWithNavController(bottom_nav, navController)
    }
}