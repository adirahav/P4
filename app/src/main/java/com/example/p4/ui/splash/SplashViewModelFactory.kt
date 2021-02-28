package com.example.p4.ui.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p4.data.network.services.SplashService

class SplashViewModelFactory(
        private val activityContext: Context,
        private val service: SplashService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(activityContext, service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}