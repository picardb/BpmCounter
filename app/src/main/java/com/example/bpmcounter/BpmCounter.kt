package com.example.bpmcounter

import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BpmCounter(val timeoutSeconds: Long) {

    // Number of beats counted
    private var countedBeats = 0

    // Time when beat counting started
    private var bpmTimeStart: Long = 0

    // BPM
    private val _bpm = MutableLiveData<Double>()
    val bpm: LiveData<Double>
        get() = _bpm

    // Timeout timer
    private val timeoutTimer = object: CountDownTimer(timeoutSeconds * 1000, 1000) {
        override fun onTick(p0: Long) {
            // Nothing to do
        }

        override fun onFinish() {
            timeoutReached()
        }
    }

    // Counting state
    private val _isCounting = MutableLiveData<Boolean>()
    val isCounting: LiveData<Boolean>
        get() = _isCounting

    init {
        _bpm.value = 0.0
        _isCounting.value = false
    }

    fun countBeat() {
        // Save current time
        val bpmTimeCurrent = SystemClock.elapsedRealtime()

        // On first beat, save starting time
        if (countedBeats == 0) {
            bpmTimeStart = bpmTimeCurrent
        }

        // Update beat counter
        countedBeats++

        // Finally, update the BPM value
        if (bpmTimeCurrent != bpmTimeStart) {
            val intervals = countedBeats - 1
            val timeSeconds = (bpmTimeCurrent - bpmTimeStart) / 1000.0
            _bpm.value = (60.0 * intervals) / timeSeconds
        } else {
            _bpm.value = 0.0
        }

        // Start or restart timeout
        _isCounting.value = true
        timeoutTimer.cancel()
        timeoutTimer.start()
    }

    private fun timeoutReached() {
        countedBeats = 0
        _isCounting.value = false
    }
}