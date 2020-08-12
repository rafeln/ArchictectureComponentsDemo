package com.example.architecturecomponents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturecomponents.model.bo.User
import com.example.architecturecomponents.model.service.network.NoInternetException
import com.example.architecturecomponents.model.service.repository.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    var users: LiveData<List<User>>? = null
    var messageError: LiveData<String>? = null

    fun getUsers() {
        try {
            users = userRepository.getUsers()
        } catch (e: Exception) {
            messageError = MutableLiveData<String>().apply { "Server Error, please try again later" }
        } catch (e: NoInternetException) {
            messageError = MutableLiveData<String>().apply { "Please check your internet connection or try again later" }
        }
    }
}