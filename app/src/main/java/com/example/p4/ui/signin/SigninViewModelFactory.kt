package com.example.p4.ui.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p4.data.network.services.SigninService

class SigninViewModelFactory(
        private val activityContext: Context,
        private val service: SigninService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SigninViewModel::class.java)) {
            return SigninViewModel(activityContext, service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}