package com.example.p4.ui.procedure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p4.data.network.services.ProcedureService

class ProcedureViewModelFactory(
        private val service: ProcedureService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProcedureViewModel::class.java)) {
            return ProcedureViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}