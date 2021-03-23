package com.example.bpmcounter

import android.util.Log
import androidx.lifecycle.ViewModel

class BpmViewModel: ViewModel() {
    fun onTap() {
        Log.i("BpmViewModel", "Tap")
    }
}