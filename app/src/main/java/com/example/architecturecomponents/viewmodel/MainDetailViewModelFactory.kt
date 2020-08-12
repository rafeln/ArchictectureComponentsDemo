package com.example.architecturecomponents.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecturecomponents.model.bo.User

class MainDetailViewModelFactory(private val user: User): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainDetailViewModel(user) as T
    }
}