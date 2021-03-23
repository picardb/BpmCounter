package com.example.bpmcounter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class BpmViewModel: ViewModel() {
    private val bpmCounter = BpmCounter(5)

    val bpm: LiveData<String> = Transformations.map(bpmCounter.bpm) { bpmValToString(it) }
    val timeoutEvent: LiveData<Boolean> = Transformations.map(bpmCounter.eventTimeout) { it }

    fun onTap() {
        Log.i("BpmViewModel", "Tap")
        bpmCounter.countBeat()
    }

    fun onTimeoutComplete() {
        bpmCounter.onTimeoutComplete()
    }

    private fun bpmValToString(bpmValue: Double): String {
        return if (bpmValue == 0.0) {
            "..."
        } else {
            bpmValue.format(2)
        }
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}