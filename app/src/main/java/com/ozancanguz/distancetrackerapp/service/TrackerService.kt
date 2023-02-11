package com.ozancanguz.distancetrackerapp.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.ozancanguz.distancetrackerapp.utils.Constants.ACTION_SERVICE_START
import com.ozancanguz.distancetrackerapp.utils.Constants.ACTION_SERVICE_STOP

class TrackerService:LifecycleService() {
    companion object {
        val started = MutableLiveData<Boolean>()

    }

    override fun onCreate() {
       setInitialValues()
        super.onCreate()
    }

    private fun setInitialValues() {
        started.postValue(false)

    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_SERVICE_START -> {
                    started.postValue(true)
                }
                ACTION_SERVICE_STOP -> {
                    started.postValue(false)
                }
                else -> {
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}