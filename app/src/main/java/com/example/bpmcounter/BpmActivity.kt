package com.example.bpmcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bpmcounter.databinding.ActivityBpmBinding

class BpmActivity : AppCompatActivity() {
    lateinit var viewModel: BpmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate View and setup data binding
        val binding: ActivityBpmBinding = ActivityBpmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create ViewModel
        viewModel = ViewModelProvider(this).get(BpmViewModel::class.java)
        binding.bpmViewModel = viewModel
    }
}