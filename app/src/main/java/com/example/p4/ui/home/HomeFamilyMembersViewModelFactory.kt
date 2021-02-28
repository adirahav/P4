package com.example.p4.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p4.data.network.services.HomeService

class HomeFamilyMembersViewModelFactory(// service
        private val service: HomeService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFamilyMembersViewModel::class.java)) {
            return HomeFamilyMembersViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}