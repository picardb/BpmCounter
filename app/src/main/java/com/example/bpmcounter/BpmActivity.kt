package com.example.bpmcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bpmcounter.databinding.ActivityBpmBinding

class BpmActivity : AppCompatActivity() {
    lateinit var binding: ActivityBpmBinding
    lateinit var viewModel: BpmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate View and setup data binding
        binding = ActivityBpmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create ViewModel
        viewModel = ViewModelProvider(this).get(BpmViewModel::class.java)
        binding.bpmViewModel = viewModel
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        // BPM
        viewModel.bpm.observe(this, Observer {bpm ->
            binding.tvBpmValue.text = bpm
        })
        // Timeout event
        viewModel.timeoutEvent.observe(this, Observer { event ->
            // TODO
            if (event == true) {
                Toast.makeText(this, "Timeout reached !", Toast.LENGTH_SHORT).show()
                viewModel.onTimeoutComplete()
            }
        })
    }
}